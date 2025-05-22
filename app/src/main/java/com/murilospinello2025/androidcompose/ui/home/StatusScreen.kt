package com.murilospinello2025.androidcompose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.murilospinello2025.androidcompose.domain.model.StatusItem
import com.murilospinello2025.androidcompose.domain.model.sampleStatuses
import com.murilospinello2025.androidcompose.ui.theme.Dimens

@Preview
@Composable
fun StatusScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = Dimens.spacingS)
    ) {
        items(sampleStatuses) { status ->
            when (status) {
                is StatusItem.MyStatus -> MyStatusItem()
                is StatusItem.ContactStatus -> ContactStatusItem(status)
            }
        }
    }
}

@Composable
fun MyStatusItem() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.spacingL, vertical = Dimens.spacingS),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://i.pinimg.com/236x/19/8a/bd/198abd0730e616bf1c3afc692d16f2ac.jpg"),
                contentDescription = "Meu Status",
                modifier = Modifier
                    .size(Dimens.chatImageSize)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(Dimens.spacingM))
            Column {
                Text("Meu Status", style = MaterialTheme.typography.titleMedium)
                Text("agora mesmo", style = MaterialTheme.typography.bodySmall)
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .padding(top = Dimens.spacingM, bottom = Dimens.spacingXS)
                .fillMaxWidth()
        )

        Text(
            text = "ATUALIZAÇÕES RECENTES",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .padding(horizontal = Dimens.spacingL, vertical = Dimens.spacingS)
        )
    }

}

@Composable
fun ContactStatusItem(status: StatusItem.ContactStatus) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.spacingL, vertical = Dimens.spacingS),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(status.profileImageUrl),
            contentDescription = status.name,
            modifier = Modifier
                .size(Dimens.chatImageSize)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(Dimens.spacingM))
        Column {
            Text(status.name, style = MaterialTheme.typography.titleMedium)
            Text(status.time, style = MaterialTheme.typography.bodySmall)
        }
    }
}


