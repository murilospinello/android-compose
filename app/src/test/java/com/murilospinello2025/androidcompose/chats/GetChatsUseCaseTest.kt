package com.murilospinello2025.androidcompose.chats

import com.murilospinello2025.androidcompose.domain.model.ChatItem
import com.murilospinello2025.androidcompose.domain.repository.ChatsRepository
import com.murilospinello2025.androidcompose.domain.usecase.GetChatsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetChatsUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GetChatsUseCase returns expected chat list`() = runTest {
        val fakeChats = listOf(ChatItem("Murilo", "Oi", "13:00", 2, "https://url.com"))

        val repository: ChatsRepository = mockk()
        coEvery { repository.getChats() } returns flowOf(fakeChats)

        val useCase = GetChatsUseCase(repository)
        val result = useCase().first()

        assertEquals(fakeChats, result)
    }
}