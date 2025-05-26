package com.murilospinello2025.androidcompose.domain.repository

import com.murilospinello2025.androidcompose.domain.model.ChatItem
import kotlinx.coroutines.flow.Flow

interface ChatsRepository {
    fun getChats(): Flow<List<ChatItem>>
}