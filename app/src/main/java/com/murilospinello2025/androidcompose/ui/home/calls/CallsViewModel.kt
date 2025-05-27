package com.murilospinello2025.androidcompose.ui.home.calls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murilospinello2025.androidcompose.domain.model.CallItem
import com.murilospinello2025.androidcompose.domain.usecase.GetCallsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CallsViewModel(
    val getCallsUseCase: GetCallsUseCase
): ViewModel() {

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    private val _calls = MutableStateFlow<List<CallItem>>(emptyList())
    val calls: StateFlow<List<CallItem>> = _calls

    fun getCalls() {
        viewModelScope.launch {
            getCallsUseCase()
                .catch { e -> _error.value = e.message }
                .collect { chatsList ->
                    _calls.value = chatsList
                }
        }
    }
}