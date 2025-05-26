package com.murilospinello2025.androidcompose.domain.usecase

import com.murilospinello2025.androidcompose.domain.model.CallItem
import com.murilospinello2025.androidcompose.domain.repository.CallsRepository
import kotlinx.coroutines.flow.Flow

class GetCallsUseCase(private val repository: CallsRepository) {
    operator fun invoke(): Flow<List<CallItem>> = repository.getCalls()
}