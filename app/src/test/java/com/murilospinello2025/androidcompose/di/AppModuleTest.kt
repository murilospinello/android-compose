package com.murilospinello2025.androidcompose.di

import com.murilospinello2025.androidcompose.ui.home.MainViewModel
import com.murilospinello2025.androidcompose.ui.home.calls.CallsViewModel
import com.murilospinello2025.androidcompose.ui.home.chats.ChatsViewModel
import com.murilospinello2025.androidcompose.ui.home.status.StatusViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get

@OptIn(ExperimentalCoroutinesApi::class)
class AppModuleTest : KoinTest {

    @Before
    fun setup() {
        startKoin {
            modules(appModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should resolve MainViewModel`() = runTest {
        val viewModel = get<MainViewModel>()
        assertNotNull(viewModel)
    }

    @Test
    fun `should resolve ChatsViewModel`() = runTest {
        val viewModel = get<ChatsViewModel>()
        assertNotNull(viewModel)
    }

    @Test
    fun `should resolve CallsViewModel`() = runTest {
        val viewModel = get<CallsViewModel>()
        assertNotNull(viewModel)
    }

    @Test
    fun `should resolve StatusViewModel`() = runTest {
        val viewModel = get<StatusViewModel>()
        assertNotNull(viewModel)
    }
}
