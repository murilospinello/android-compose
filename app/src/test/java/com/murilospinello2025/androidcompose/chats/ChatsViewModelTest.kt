package com.murilospinello2025.androidcompose.chats

import com.murilospinello2025.androidcompose.domain.model.ChatItem
import com.murilospinello2025.androidcompose.domain.usecase.GetChatsUseCase
import com.murilospinello2025.androidcompose.ui.home.chats.ChatsUiState
import com.murilospinello2025.androidcompose.ui.home.chats.ChatsUiStateViewModel
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
    private val viewModel = ChatsUiStateViewModel(useCase)

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

        val state = viewModel.uiState.value
        assert(state is ChatsUiState.Success)
        assertEquals(fakeChats, (state as ChatsUiState.Success).chats)
    }

    @Test
    fun `getChats emits empty list when flow is empty`() = runTest {
        val errorMessage = "Erro de teste"
        coEvery { useCase.invoke() } returns flow {
            throw RuntimeException(errorMessage)
        }

        viewModel.getChats()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is ChatsUiState.Error)
        assertEquals(errorMessage, (state as ChatsUiState.Error).msg)
    }

    @Test
    fun `getChats emits Loading state before collecting`() = runTest {
        coEvery { useCase.invoke() } returns flowOf(emptyList())

        viewModel.getChats()

        assert(viewModel.uiState.value is ChatsUiState.Loading)

        advanceUntilIdle()

        assert(viewModel.uiState.value is ChatsUiState.Success)
    }
}