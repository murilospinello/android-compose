package com.murilospinello2025.androidcompose.ui.home

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
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
import coil.compose.rememberAsyncImagePainter
import com.murilospinello2025.androidcompose.domain.model.CallDirection
import com.murilospinello2025.androidcompose.domain.model.CallItem
import com.murilospinello2025.androidcompose.domain.model.sampleCalls
import com.murilospinello2025.androidcompose.ui.theme.Dimens

@Preview
@Composable
fun CallsScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                text = "Ligações",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = Dimens.spacingL, vertical = Dimens.spacingS)
            )
        }

        item {
            Text(
                text = "Favoritos",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(horizontal = Dimens.spacingL, vertical = Dimens.spacingS)
            )
        }

        items(sampleCalls.filterIsInstance<CallItem.Favorite>()) { call ->
            FavoriteCallItem(call)
        }

        item {
            Text(
                text = "Recentes",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(horizontal = Dimens.spacingL, vertical = Dimens.spacingS)
            )
        }

        items(sampleCalls.filterIsInstance<CallItem.Recent>()) { call ->
            RecentCallItem(call)
        }
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


