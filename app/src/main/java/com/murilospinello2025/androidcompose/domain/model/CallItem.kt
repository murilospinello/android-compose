package com.murilospinello2025.androidcompose.domain.model


sealed class CallItem {
    data class Favorite(
        val name: String,
        val profileImageUrl: String
    ) : CallItem()

    data class Recent(
        val name: String,
        val profileImageUrl: String,
        val direction: CallDirection,
        val dateTime: String,
        val isVideoCall: Boolean
    ) : CallItem()
}

enum class CallDirection {
    INCOMING,
    OUTGOING,
    MISSED
}
