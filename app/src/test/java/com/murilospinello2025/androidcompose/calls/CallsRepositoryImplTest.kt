package com.murilospinello2025.androidcompose.calls

import com.murilospinello2025.androidcompose.data.local.CallsDataSourceMock
import com.murilospinello2025.androidcompose.data.repository.CallsRepositoryImpl
import com.murilospinello2025.androidcompose.domain.model.CallDirection
import com.murilospinello2025.androidcompose.domain.model.CallItem
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CallsRepositoryImplTest {

    @Test
    fun `getCalls returns expected calls list`() = runTest {
        val fakeCalls = listOf(
            CallItem.Recent(
                "Murilo",
                "https://url.com",
                CallDirection.INCOMING,
                "Hoje 13:00",
                false
            )
        )

        val dataSource: CallsDataSourceMock = mockk()
        coEvery { dataSource.getCalls() } returns flowOf(fakeCalls)

        val repository = CallsRepositoryImpl(dataSource)
        val result = repository.getCalls().first()

        assertEquals(fakeCalls, result)
    }
}
