package com.murilospinello2025.androidcompose.ui.home.chats

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.murilospinello2025.androidcompose.domain.model.ChatItem
import com.murilospinello2025.androidcompose.ui.theme.Dimens
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatsScreen() {
    val viewModel: ChatsViewModel = koinViewModel()
    val chats by viewModel.chats.collectAsStateWithLifecycle()
    ChatsScreenColumn(chats)
}

@Preview
@Composable
fun ChatsScreenPreview() {
    ChatsScreenColumn(sampleChatsPreview)
}

@Composable
fun ChatsScreenColumn(
    list: List<ChatItem>
) {
    val horizontalColorDivider = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = Dimens.spacingS)
    ) {
        list.forEach {
            item {
                ChatRow(chat = it)
                HorizontalDivider(
                    modifier = Modifier
                        .padding(start = Dimens.chatDividerStartPadding)
                        .fillMaxWidth(),
                    thickness = Dimens.dividerThickness,
                    color = horizontalColorDivider
                )

            }
        }
    }
}

@Composable
fun ChatRow(chat: ChatItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.spacingM, vertical = Dimens.spacingS),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(chat.profileImageUrl),
            contentDescription = chat.senderName,
            modifier = Modifier
                .size(Dimens.chatImageSize)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(Dimens.spacingM))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = chat.senderName, style = MaterialTheme.typography.titleMedium)
            Text(
                text = chat.lastMessage,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = chat.time,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )

            if (chat.unreadCount > 0) {
                Spacer(modifier = Modifier.height(Dimens.spacingXS))
                Badge {
                    Text("${chat.unreadCount}")
                }
            }
        }
    }
}

private val sampleChatsPreview = listOf(
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