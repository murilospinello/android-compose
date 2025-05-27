package com.murilospinello2025.androidcompose.chats

import com.murilospinello2025.androidcompose.domain.model.ChatItem
import com.murilospinello2025.androidcompose.domain.usecase.GetChatsUseCase
import com.murilospinello2025.androidcompose.ui.home.chats.ChatsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ChatsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val useCase: GetChatsUseCase = mockk()
    private val viewModel = ChatsViewModel(useCase)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getChats updates chats state`() = runTest {
        val fakeChats = listOf(
            ChatItem("Murilo", "Oi", "13:00", 2, "https://url.com")
        )
        
        coEvery { useCase.invoke() } returns flowOf(fakeChats)

        viewModel.getChats()
        advanceUntilIdle()

        assertEquals(fakeChats, viewModel.chats.value)
        assertEquals(null, viewModel.error.value)
    }

    @Test
    fun `getChats emits empty list when flow is empty`() = runTest {
        coEvery { useCase.invoke() } returns flowOf(emptyList())

        viewModel.getChats()
        advanceUntilIdle()

        assertEquals(emptyList<ChatItem>(), viewModel.chats.value)
        assertEquals(null, viewModel.error.value)
    }

    @Test
    fun `getChats sets error message on exception`() = runTest {
        val errorMessage = "Erro de teste"
        coEvery { useCase.invoke() } returns flow {
            throw RuntimeException(errorMessage)
        }

        viewModel.getChats()
        advanceUntilIdle()

        assertEquals(emptyList<ChatItem>(), viewModel.chats.value)
        assertEquals(errorMessage, viewModel.error.value)
    }
}