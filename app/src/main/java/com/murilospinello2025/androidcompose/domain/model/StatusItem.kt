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


val sampleStatuses = listOf(
    StatusItem.MyStatus,
    StatusItem.ContactStatus(
        id = "1",
        name = "Estenio",
        time = "há 10 min",
        profileImageUrl = "https://www.psitto.com.br/wp-content/uploads/2021/01/como-conviver-pessoas-dificeis.jpg"
    ),
    StatusItem.ContactStatus(
        id = "2",
        name = "Nayara",
        time = "há 23 min",
        profileImageUrl = "https://www.pensarcontemporaneo.com/content/uploads/2023/01/image-1.jpg"
    ),
    StatusItem.ContactStatus(
        id = "3",
        name = "Herick",
        time = "há 1h",
        profileImageUrl = "https://i.pinimg.com/736x/fd/fc/ef/fdfcefc24e58a4e3ed4dd6099d530353.jpg"
    )
)
