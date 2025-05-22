package com.murilospinello2025.androidcompose.domain.model

class ChatItem(
    val senderName: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int,
    val profileImageUrl: String
)

val sampleChats = listOf(
    ChatItem(
        senderName = "Murilo",
        lastMessage = "E aí, já terminou o app clone?",
        time = "12:45",
        unreadCount = 2,
        profileImageUrl = "https://i.pinimg.com/236x/19/8a/bd/198abd0730e616bf1c3afc692d16f2ac.jpg"
    ),
    ChatItem(
        senderName = "Estenio",
        lastMessage = "Beleza, vamos marcar pra amanhã!",
        time = "11:30",
        unreadCount = 0,
        profileImageUrl = "https://www.psitto.com.br/wp-content/uploads/2021/01/como-conviver-pessoas-dificeis.jpg"
    ),
    ChatItem(
        senderName = "Nayara",
        lastMessage = "Vou te mandar os arquivos aqui",
        time = "Ontem",
        unreadCount = 1,
        profileImageUrl = "https://www.pensarcontemporaneo.com/content/uploads/2023/01/image-1.jpg"
    ),
    ChatItem(
        senderName = "Herick",
        lastMessage = "Olha essa conversa aqui",
        time = "Ontem",
        unreadCount = 1,
        profileImageUrl = "https://i.pinimg.com/736x/fd/fc/ef/fdfcefc24e58a4e3ed4dd6099d530353.jpg"
    )
)
