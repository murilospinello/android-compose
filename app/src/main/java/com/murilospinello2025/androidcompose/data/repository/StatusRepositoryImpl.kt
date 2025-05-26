package com.murilospinello2025.androidcompose.data.repository


import com.murilospinello2025.androidcompose.data.local.StatusDataSourceMock
import com.murilospinello2025.androidcompose.domain.model.StatusItem
import com.murilospinello2025.androidcompose.domain.repository.StatusRepository
import kotlinx.coroutines.flow.Flow

class StatusRepositoryImpl(
    private val dataSource: StatusDataSourceMock
) : StatusRepository {
    override fun getStatus(): Flow<List<StatusItem>> = dataSource.getStatus()
}