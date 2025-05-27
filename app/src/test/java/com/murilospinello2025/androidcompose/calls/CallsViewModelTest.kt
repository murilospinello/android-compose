package com.murilospinello2025.androidcompose.calls

import com.murilospinello2025.androidcompose.domain.model.CallDirection
import com.murilospinello2025.androidcompose.domain.model.CallItem
import com.murilospinello2025.androidcompose.domain.usecase.GetCallsUseCase
import com.murilospinello2025.androidcompose.ui.home.calls.CallsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class CallsViewModelTest {


    private val testDispatcher = StandardTestDispatcher()
    private val useCase: GetCallsUseCase = mockk()
    private val viewModel = CallsViewModel(useCase)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCalls updates calls state`() = runTest {
        val fakeCalls = listOf(
            CallItem.Recent("Murilo", "https://url.com", CallDirection.INCOMING, "Hoje 13:00", false)
        )

        coEvery { useCase.invoke() } returns flowOf(fakeCalls)

        viewModel.getCalls()
        advanceUntilIdle()

        assertEquals(fakeCalls, viewModel.calls.value)
        assertEquals(null, viewModel.error.value)
    }

    @Test
    fun `getCalls updates calls state favorite`() = runTest {
        val fakeCalls = listOf(
            CallItem.Favorite("Murilo", "https://url.com")
        )

        coEvery { useCase.invoke() } returns flowOf(fakeCalls)

        viewModel.getCalls()
        advanceUntilIdle()

        assertEquals(fakeCalls, viewModel.calls.value)
        assertEquals(null, viewModel.error.value)
    }

    @Test
    fun `getCalls emits empty list when flow is empty`() = runTest {
        coEvery { useCase.invoke() } returns flowOf(emptyList())

        viewModel.getCalls()
        advanceUntilIdle()

        assertEquals(emptyList<CallItem>(), viewModel.calls.value)
        assertEquals(null, viewModel.error.value)
    }

    @Test
    fun `getCalls sets error message on exception`() = runTest {
        val errorMessage = "Erro de teste"
        coEvery { useCase.invoke() } returns flow {
            throw RuntimeException(errorMessage)
        }

        viewModel.getCalls()
        advanceUntilIdle()

        assertEquals(emptyList<CallItem>(), viewModel.calls.value)
        assertEquals(errorMessage, viewModel.error.value)
    }
}