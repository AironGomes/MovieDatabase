package com.airongomes.moviedatabase.di

import com.airongomes.moviedatabase.domain.remote.api.TMDbApi
import com.airongomes.moviedatabase.domain.remote.instantiateApi
import com.airongomes.moviedatabase.domain.source.MoviePagingSource
import com.airongomes.moviedatabase.domain.repository.RemoteDataSource
import com.airongomes.moviedatabase.domain.repository.Repository
import com.airongomes.moviedatabase.viewModel.DetailViewModel
import com.airongomes.moviedatabase.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val defaultModule = module {
    single { instantiateApi(TMDbApi::class.java) }
    single { RemoteDataSource(get()) }
    single { Repository(get()) }
    single { MoviePagingSource(get()) }

    viewModel { DetailViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}