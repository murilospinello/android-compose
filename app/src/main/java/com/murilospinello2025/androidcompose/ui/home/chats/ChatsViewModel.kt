package com.murilospinello2025.androidcompose.ui.home.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murilospinello2025.androidcompose.domain.model.ChatItem
import com.murilospinello2025.androidcompose.domain.usecase.GetChatsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ChatsViewModel(val getChatsUseCase: GetChatsUseCase) : ViewModel() {


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _chats = MutableStateFlow<List<ChatItem>>(emptyList())
    val chats: StateFlow<List<ChatItem>> = _chats

    fun getChats() {
        viewModelScope.launch {
            getChatsUseCase()
                .catch { e -> _error.value = e.message }
                .collect { chatsList ->
                    _chats.value = chatsList
                }
        }
    }
}