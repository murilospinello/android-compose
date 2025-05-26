package com.murilospinello2025.androidcompose.ui.home.calls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murilospinello2025.androidcompose.domain.model.CallItem
import com.murilospinello2025.androidcompose.domain.usecase.GetCallsUseCase
import com.murilospinello2025.emptyString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn

class CallsViewModel(
    getCallsUseCase: GetCallsUseCase
): ViewModel() {

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    val calls: StateFlow<List<CallItem>> = getCallsUseCase()
        .catch { e ->
            _error.value = e.message ?: "Erro desconhecido"
            emit(emptyList())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}