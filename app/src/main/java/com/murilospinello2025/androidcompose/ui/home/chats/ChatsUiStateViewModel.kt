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

    private val _uiState = MutableStateFlow<ChatsUiState>(ChatsUiState.Loading)
    val uiState: StateFlow<ChatsUiState> = _uiState

    fun getChats() {
        viewModelScope.launch {
            getChatsUseCase()
                .onStart { _uiState.value = ChatsUiState.Loading }
                .catch { _uiState.value = ChatsUiState.Error(it.message ?: "Unknown error") }
                .collect { _uiState.value = ChatsUiState.Success(it) }
        }
    }
}