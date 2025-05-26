package com.murilospinello2025.androidcompose.domain.repository

import com.murilospinello2025.androidcompose.domain.model.CallItem
import kotlinx.coroutines.flow.Flow

interface CallsRepository {
    fun getCalls(): Flow<List<CallItem>>
}