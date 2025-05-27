package com.murilospinello2025.androidcompose.status

import com.murilospinello2025.androidcompose.domain.model.StatusItem
import com.murilospinello2025.androidcompose.domain.repository.StatusRepository
import com.murilospinello2025.androidcompose.domain.usecase.GetStatusUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetStatusUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `GetStatusUseCase returns expected status list`() = runTest {
        val fakeStatus = listOf(
            StatusItem.ContactStatus("Murilo", "Hoje", "10:10", "https://url.com")
        )

        val repository: StatusRepository = mockk()
        coEvery { repository.getStatus() } returns flowOf(fakeStatus)

        val useCase = GetStatusUseCase(repository)

        val result = useCase().first()

        assertEquals(fakeStatus, result)
    }
}