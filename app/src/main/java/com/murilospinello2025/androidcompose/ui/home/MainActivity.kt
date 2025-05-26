package com.murilospinello2025.androidcompose.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.murilospinello2025.androidcompose.ui.home.calls.CallsScreen
import com.murilospinello2025.androidcompose.ui.home.chats.ChatsScreen
import com.murilospinello2025.androidcompose.ui.home.status.StatusScreen
import com.murilospinello2025.androidcompose.ui.theme.AndroidComposeMuriloSpinello2025Theme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidComposeMuriloSpinello2025Theme {
                WhatsAppWithSwipe(mainViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatsAppWithSwipe(mainViewModel: MainViewModel) {
    val tabs = WhatsAppTab.items
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val title by mainViewModel.titlePage.collectAsStateWithLifecycle()

    LaunchedEffect(pagerState.currentPage) {
        mainViewModel.setTitlePage(tabs[pagerState.currentPage].title)
    }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title) })
        },
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        icon = { Icon(tab.icon, contentDescription = tab.title) },
                        label = { Text(tab.title) },
                        selected = index == pagerState.currentPage,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
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
            when (tabs[page]) {
                is WhatsAppTab.Chats -> ChatsScreen()
                is WhatsAppTab.Status -> StatusScreen()
                is WhatsAppTab.Calls -> CallsScreen()
            }
        }
    }
}

@Preview
@Composable
fun WhatsAppWithSwipePreview() {
    val previewViewModel = remember { MainViewModel() }
    WhatsAppWithSwipe(mainViewModel = previewViewModel)
}
