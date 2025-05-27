package com.murilospinello2025.androidcompose.status

import com.murilospinello2025.androidcompose.domain.model.StatusItem
import com.murilospinello2025.androidcompose.domain.usecase.GetStatusUseCase
import com.murilospinello2025.androidcompose.ui.home.status.StatusViewModel
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
class StatusViewModelTest {


    private val testDispatcher = StandardTestDispatcher()
    private val useCase: GetStatusUseCase = mockk()
    private val viewModel = StatusViewModel(useCase)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `getStatus updates status state`() = runTest {
        val fakeStatus = listOf(
            StatusItem.ContactStatus("Murilo", "Hoje", "10:10", "https://url.com")
        )

        coEvery { useCase.invoke() } returns flowOf(fakeStatus)

        viewModel.getStatus()
        advanceUntilIdle()

        assertEquals(fakeStatus, viewModel.status.value)
        assertEquals(null, viewModel.error.value)
    }


    @Test
    fun `getStatus updates status state MyStatus`() = runTest {
        val fakeStatus = listOf(
            StatusItem.MyStatus
        )

        coEvery { useCase.invoke() } returns flowOf(fakeStatus)

        viewModel.getStatus()
        advanceUntilIdle()

        assertEquals(fakeStatus, viewModel.status.value)
    }

    @Test
    fun `getStatus emits empty list when flow is empty`() = runTest {

        coEvery { useCase.invoke() } returns flowOf(emptyList())

        viewModel.getStatus()
        advanceUntilIdle()

        assertEquals(emptyList<StatusItem>(), viewModel.status.value)
        assertEquals(null, viewModel.error.value)
    }

    @Test
    fun `getStatus sets error message on exception`() = runTest {

        val errorMessage = "Erro de teste"
        coEvery { useCase.invoke() } returns flow {
            throw RuntimeException(errorMessage)
        }

        viewModel.getStatus()
        advanceUntilIdle()

        assertEquals(emptyList<StatusItem>(), viewModel.status.value)
        assertEquals(errorMessage, viewModel.error.value)
    }

}