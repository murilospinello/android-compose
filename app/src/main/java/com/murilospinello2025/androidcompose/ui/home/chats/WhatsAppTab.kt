package com.murilospinello2025.androidcompose.ui.home.chats

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class WhatsAppTab(
    val title: String,
    val icon: ImageVector
) {
    data object Chats : WhatsAppTab("Conversas", Icons.Filled.Email)
    data object Status : WhatsAppTab("Status", Icons.Filled.Star)
    data object Calls : WhatsAppTab("Chamadas", Icons.Filled.Call)

    companion object {
        val items = listOf(Chats, Status, Calls)
    }
}
