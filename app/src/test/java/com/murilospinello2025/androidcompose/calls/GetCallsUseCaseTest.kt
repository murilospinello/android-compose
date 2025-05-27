package com.murilospinello2025.androidcompose.calls

import com.murilospinello2025.androidcompose.domain.model.CallDirection
import com.murilospinello2025.androidcompose.domain.model.CallItem
import com.murilospinello2025.androidcompose.domain.repository.CallsRepository
import com.murilospinello2025.androidcompose.domain.usecase.GetCallsUseCase
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
class GetCallsUseCaseTest {

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
    fun `GetCallsUseCase returns expected calls list`() = runTest {
        val fakeCalls = listOf(
            CallItem.Recent(
                "Murilo",
                "https://url.com",
                CallDirection.INCOMING,
                "Hoje 13:00",
                false
            )
        )

        val repository: CallsRepository = mockk()
        coEvery { repository.getCalls() } returns flowOf(fakeCalls)

        val useCase = GetCallsUseCase(repository)

        val result = useCase().first()

        assertEquals(fakeCalls, result)
    }
}