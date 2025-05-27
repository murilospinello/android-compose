package com.murilospinello2025.androidcompose.chats

import com.murilospinello2025.androidcompose.data.local.ChatsDataSourceMock
import com.murilospinello2025.androidcompose.data.repository.ChatsRepositoryImpl
import com.murilospinello2025.androidcompose.domain.model.ChatItem
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ChatsRepositoryImplTest {

    @Test
    fun `getChats returns expected chat list`() = runTest {
        val fakeChats = listOf(
            ChatItem("Murilo", "Mensagem", "13:00", 1, "https://url.com")
        )

        val dataSource: ChatsDataSourceMock = mockk()
        coEvery { dataSource.getChats() } returns flowOf(fakeChats)

        val repository = ChatsRepositoryImpl(dataSource)
        val result = repository.getChats().first()

        assertEquals(fakeChats, result)
    }
}
