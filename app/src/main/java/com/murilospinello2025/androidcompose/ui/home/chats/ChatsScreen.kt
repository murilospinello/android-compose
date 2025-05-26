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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.murilospinello2025.androidcompose.domain.model.ChatItem
import com.murilospinello2025.androidcompose.domain.model.sampleChats
import com.murilospinello2025.androidcompose.ui.theme.Dimens
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun ChatsScreen() {
    val viewModel: ChatsViewModel = koinViewModel()
    val horizontalColorDivider = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = Dimens.spacingS)
    ) {
        sampleChats.forEach {
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
