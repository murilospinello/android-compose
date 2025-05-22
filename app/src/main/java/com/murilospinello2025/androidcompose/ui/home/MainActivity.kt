package com.murilospinello2025.androidcompose.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.murilospinello2025.androidcompose.ui.theme.AndroidComposeMuriloSpinello2025Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidComposeMuriloSpinello2025Theme {
                WhatsAppWithSwipe()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun WhatsAppWithSwipe() {
    val tabs = listOf("Conversas", "Status", "Chamadas")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Column {
                TopAppBar(title = { Text("WhatsApp Murilo") })
            }
        },
        bottomBar = {
            NavigationBar {
                tabs.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = screen) },
                        label = { Text(screen) },
                        selected = screen == tabs[pagerState.currentPage],
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(tabs.indexOf(screen))
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page ->
            when (page) {
                0 -> ChatsScreen()
                1 -> StatusScreen()
                2 -> OtherScreen()
            }
        }

    }
}

@Composable
fun ChatsScreen() {
    Text("Lista de conversas")
}

@Composable
fun StatusScreen() {
    Text("Status dos contatos")
}

@Composable
fun OtherScreen() {
    Text("Histórico de chamadas")
}
