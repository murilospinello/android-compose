package com.murilospinello2025.androidcompose.ui.home.status

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murilospinello2025.androidcompose.domain.model.StatusItem
import com.murilospinello2025.androidcompose.domain.usecase.GetStatusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn

class StatusViewModel(getStatusUseCase: GetStatusUseCase): ViewModel() {

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    val status: StateFlow<List<StatusItem>> = getStatusUseCase()
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