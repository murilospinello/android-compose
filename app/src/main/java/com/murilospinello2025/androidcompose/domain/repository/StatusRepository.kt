package com.murilospinello2025.androidcompose.domain.repository

import com.murilospinello2025.androidcompose.domain.model.StatusItem
import kotlinx.coroutines.flow.Flow

interface StatusRepository {
    fun getStatus(): Flow<List<StatusItem>>
}