package com.murilospinello2025.androidcompose.ui.home.status

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murilospinello2025.androidcompose.domain.model.StatusItem
import com.murilospinello2025.androidcompose.domain.usecase.GetStatusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class StatusViewModel(val getStatusUseCase: GetStatusUseCase) : ViewModel() {

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _status = MutableStateFlow<List<StatusItem>>(emptyList())
    val status: StateFlow<List<StatusItem>> = _status

    fun getStatus() {
        viewModelScope.launch {
            getStatusUseCase()
                .catch { e -> _error.value = e.message }
                .collect { chatsList ->
                    _status.value = chatsList
                }
        }
    }
}