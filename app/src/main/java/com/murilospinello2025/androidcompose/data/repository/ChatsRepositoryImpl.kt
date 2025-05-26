package com.murilospinello2025.androidcompose.data.repository


import com.murilospinello2025.androidcompose.data.local.ChatsDataSourceMock
import com.murilospinello2025.androidcompose.domain.model.ChatItem
import com.murilospinello2025.androidcompose.domain.repository.ChatsRepository
import kotlinx.coroutines.flow.Flow

class ChatsRepositoryImpl(
    private val dataSource: ChatsDataSourceMock
) : ChatsRepository {
    override fun getChats(): Flow<List<ChatItem>> = dataSource.getChats()
}