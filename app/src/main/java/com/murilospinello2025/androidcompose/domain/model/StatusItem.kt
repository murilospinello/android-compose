package com.murilospinello2025.androidcompose.domain.model

sealed class StatusItem {
    data class ContactStatus(
        val id: String,
        val name: String,
        val time: String,
        val profileImageUrl: String
    ) : StatusItem()

    data object MyStatus : StatusItem()
}
