package com.murilospinello2025.androidcompose.ui.home.calls

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.murilospinello2025.androidcompose.domain.model.CallDirection
import com.murilospinello2025.androidcompose.domain.model.CallItem
import com.murilospinello2025.androidcompose.ui.theme.Dimens
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue


@Composable
fun CallsScreen() {
    val viewModel: CallsViewModel = koinViewModel()
    val calls by viewModel.calls.collectAsStateWithLifecycle()

    val favorites = calls.filterIsInstance<CallItem.Favorite>()
    val recents = calls.filterIsInstance<CallItem.Recent>()


    CallsColumn {
        favoriteSection(favorites)
        recentSection(recents)
    }
}

@Preview
@Composable
fun CallsScreenPreview() {
    val favorites = sampleCallsPreview.filterIsInstance<CallItem.Favorite>()
    val recents = sampleCallsPreview.filterIsInstance<CallItem.Recent>()

    CallsColumn {
        favoriteSection(favorites)
        recentSection(recents)
    }
}

@Composable
fun CallsColumn(content: LazyListScope.() -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                text = "Ligações",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(
                    horizontal = Dimens.spacingL,
                    vertical = Dimens.spacingS
                )
            )
        }

        content()
    }
}

fun LazyListScope.favoriteSection(favorites: List<CallItem.Favorite>) {
    if (favorites.isNotEmpty()) {
        item {
            Text(
                text = "Favoritos",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(horizontal = Dimens.spacingL)
            )
        }
        items(favorites) { FavoriteCallItem(it) }
    }
}

fun LazyListScope.recentSection(recents: List<CallItem.Recent>) {
    if (recents.isNotEmpty()) {
        item {
            Text(
                text = "Recentes",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(horizontal = Dimens.spacingL)
            )
        }
        items(recents) { RecentCallItem(it) }
    }
}

@Composable
fun FavoriteCallItem(call: CallItem.Favorite) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.spacingL, vertical = Dimens.spacingS),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(call.profileImageUrl),
            contentDescription = call.name,
            modifier = Modifier
                .size(Dimens.chatImageSize)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(Dimens.spacingM))
        Text(call.name, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.Call, contentDescription = "Chamada")
        Spacer(modifier = Modifier.width(Dimens.spacingS))
        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Vídeo")
    }
}

@Composable
fun RecentCallItem(call: CallItem.Recent) {
    val arrowIcon = when (call.direction) {
        CallDirection.INCOMING -> Icons.Default.Call
        CallDirection.OUTGOING -> Icons.AutoMirrored.Filled.ArrowBack
        CallDirection.MISSED -> Icons.AutoMirrored.Filled.ArrowForward
    }

    val arrowColor = when (call.direction) {
        CallDirection.INCOMING -> Color(0xFF00C853)
        CallDirection.OUTGOING -> Color(0xFF00C853)
        CallDirection.MISSED -> Color.Red
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.spacingL, vertical = Dimens.spacingS),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(call.profileImageUrl),
            contentDescription = call.name,
            modifier = Modifier
                .size(Dimens.chatImageSize)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(Dimens.spacingM))
        Column(modifier = Modifier.weight(1f)) {
            Text(call.name, style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = arrowIcon,
                    contentDescription = null,
                    tint = arrowColor,
                    modifier = Modifier.size(Dimens.spacingL)
                )
                Spacer(modifier = Modifier.width(Dimens.spacingXS))
                Text(call.dateTime, style = MaterialTheme.typography.bodySmall)
            }
        }
        Icon(
            imageVector = if (call.isVideoCall) Icons.Default.PlayArrow else Icons.Default.Call,
            contentDescription = null
        )
    }
}

private val sampleCallsPreview = listOf(
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


