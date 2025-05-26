package com.murilospinello2025.androidcompose.domain.usecase

import com.murilospinello2025.androidcompose.domain.model.StatusItem
import com.murilospinello2025.androidcompose.domain.repository.StatusRepository
import kotlinx.coroutines.flow.Flow

class GetStatusUseCase(private val repository: StatusRepository) {
    operator fun invoke(): Flow<List<StatusItem>> = repository.getStatus()
}