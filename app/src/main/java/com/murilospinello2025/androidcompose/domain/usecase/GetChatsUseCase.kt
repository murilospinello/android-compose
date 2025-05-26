package com.murilospinello2025.androidcompose.domain.usecase

import com.murilospinello2025.androidcompose.domain.model.ChatItem
import com.murilospinello2025.androidcompose.domain.repository.ChatsRepository
import kotlinx.coroutines.flow.Flow

class GetChatsUseCase(private val repository: ChatsRepository) {
    operator fun invoke(): Flow<List<ChatItem>> = repository.getChats()
}