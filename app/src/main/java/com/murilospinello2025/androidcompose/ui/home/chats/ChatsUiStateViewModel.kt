package com.murilospinello2025.androidcompose.ui.home.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murilospinello2025.androidcompose.domain.usecase.GetChatsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ChatsUiStateViewModel(val getChatsUseCase: GetChatsUseCase) : ViewModel() {

    private val _chats = MutableStateFlow<ChatsUiState>(ChatsUiState.Loading)
    val chats: StateFlow<ChatsUiState> = _chats

    fun getChats() {
        viewModelScope.launch {
            getChatsUseCase()
                .onStart { _chats.value = ChatsUiState.Loading }
                .catch { _chats.value = ChatsUiState.Error(it.message ?: "Unknown error") }
                .collect { _chats.value = ChatsUiState.Success(it) }
        }
    }
}