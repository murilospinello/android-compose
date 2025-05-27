package com.murilospinello2025.androidcompose.status

import com.murilospinello2025.androidcompose.data.local.StatusDataSourceMock
import com.murilospinello2025.androidcompose.data.repository.StatusRepositoryImpl
import com.murilospinello2025.androidcompose.domain.model.StatusItem
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class StatusRepositoryImplTest {

    @Test
    fun `getStatus returns expected status list`() = runTest {
        val fakeStatus = listOf(
            StatusItem.ContactStatus("Murilo", "Hoje", "1010", "https://url.com")
        )

        val dataSource: StatusDataSourceMock = mockk()
        coEvery { dataSource.getStatus() } returns flowOf(fakeStatus)

        val repository = StatusRepositoryImpl(dataSource)
        val result = repository.getStatus().first()

        assertEquals(fakeStatus, result)
    }
}
