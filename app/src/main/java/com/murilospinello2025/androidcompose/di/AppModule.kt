package com.murilospinello2025.androidcompose.di

import com.murilospinello2025.androidcompose.data.local.CallsDataSourceMock
import com.murilospinello2025.androidcompose.data.repository.CallsRepositoryImpl
import com.murilospinello2025.androidcompose.domain.repository.CallsRepository
import com.murilospinello2025.androidcompose.domain.usecase.GetCallsUseCase
import com.murilospinello2025.androidcompose.ui.home.MainViewModel
import com.murilospinello2025.androidcompose.ui.home.calls.CallsViewModel
import com.murilospinello2025.androidcompose.ui.home.chats.ChatsViewModel
import com.murilospinello2025.androidcompose.ui.home.status.StatusViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::MainViewModel)
    viewModelOf(::CallsViewModel)
    viewModelOf(::ChatsViewModel)
    viewModelOf(::StatusViewModel)

    singleOf(::GetCallsUseCase)
    singleOf(::CallsRepositoryImpl) bind CallsRepository::class
    singleOf(::CallsDataSourceMock)
}