package com.airongomes.moviedatabase.di

import com.airongomes.moviedatabase.domain.remote.api.TMDbApi
import com.airongomes.moviedatabase.domain.remote.instantiateApi
import com.airongomes.moviedatabase.repository.RemoteDataSource
import com.airongomes.moviedatabase.repository.Repository
import com.airongomes.moviedatabase.viewModel.DetailViewModel
import com.airongomes.moviedatabase.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val defaultModule = module {
    single { instantiateApi(TMDbApi::class.java) }
    single { RemoteDataSource(get()) }
    single { Repository(get()) }

    viewModel { DetailViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}