package com.murilospinello2025.androidcompose.data.local

import com.murilospinello2025.androidcompose.domain.model.CallDirection
import com.murilospinello2025.androidcompose.domain.model.CallItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CallsDataSourceMock {
    fun getCalls(): Flow<List<CallItem>> = flowOf(sampleCalls)
}

val sampleCalls = listOf(
    CallItem.Favorite(
        name = "Herick",
        profileImageUrl = "https://i.pinimg.com/736x/fd/fc/ef/fdfcefc24e58a4e3ed4dd6099d530353.jpg"
    ),
    CallItem.Favorite(
        name = "Estenio",
        profileImageUrl = "https://www.psitto.com.br/wp-content/uploads/2021/01/como-conviver-pessoas-dificeis.jpg"
    ),
    CallItem.Recent(
        name = "Nayara",
        profileImageUrl = "https://www.pensarcontemporaneo.com/content/uploads/2023/01/image-1.jpg",
        direction = CallDirection.INCOMING,
        dateTime = "Hoje 12:45",
        isVideoCall = false
    ),
    CallItem.Recent(
        name = "Herick",
        profileImageUrl = "https://i.pinimg.com/736x/fd/fc/ef/fdfcefc24e58a4e3ed4dd6099d530353.jpg",
        direction = CallDirection.OUTGOING,
        dateTime = "Ontem 11:30",
        isVideoCall = true
    )
)