package com.murilospinello2025.androidcompose.ui.home.chats

import com.murilospinello2025.androidcompose.domain.model.ChatItem

sealed interface ChatsUiState {
    object Loading : ChatsUiState
    data class Success(val chats: List<ChatItem>) : ChatsUiState
    data class Error(val msg: String) : ChatsUiState
}