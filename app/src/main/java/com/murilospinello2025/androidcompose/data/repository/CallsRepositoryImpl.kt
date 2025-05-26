package com.murilospinello2025.androidcompose.data.repository


import com.murilospinello2025.androidcompose.data.local.CallsDataSourceMock
import com.murilospinello2025.androidcompose.domain.model.CallItem
import com.murilospinello2025.androidcompose.domain.model.ChatItem
import com.murilospinello2025.androidcompose.domain.repository.CallsRepository
import kotlinx.coroutines.flow.Flow

class CallsRepositoryImpl(
    private val dataSource: CallsDataSourceMock
) : CallsRepository {
    override fun getCalls(): Flow<List<CallItem>> = dataSource.getCalls()
}