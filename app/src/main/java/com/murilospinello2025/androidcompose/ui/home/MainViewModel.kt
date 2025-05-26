package com.murilospinello2025.androidcompose.ui.home

import androidx.lifecycle.ViewModel
import com.murilospinello2025.emptyString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    private val _titlePage = MutableStateFlow<String>(emptyString())
    val titlePage = _titlePage.asStateFlow()

    fun setTitlePage(title: String) {
        _titlePage.value = "$TAG $title"
    }

    companion object {
        private const val TAG = "Whatsapp"
    }
}