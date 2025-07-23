package com.murilospinello2025.androidcompose.status

import com.murilospinello2025.androidcompose.data.local.StatusDataSourceMock
import com.murilospinello2025.androidcompose.data.local.sampleStatus
import com.murilospinello2025.androidcompose.data.repository.StatusRepositoryImpl
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

        val dataSource: StatusDataSourceMock = mockk()
        coEvery { dataSource.getStatus() } returns flowOf(sampleStatus)

        val repository = StatusRepositoryImpl(dataSource)
        val result = repository.getStatus().first()

        assertEquals(sampleStatus, result)
    }
}
