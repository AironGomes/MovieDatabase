package com.airongomes.moviedatabase.di

import com.airongomes.moviedatabase.domain.remote.api.TMDbApi
import com.airongomes.moviedatabase.domain.remote.instantiateApi
import com.airongomes.moviedatabase.domain.repository.MovieRepository
import com.airongomes.moviedatabase.domain.repository.RemoteDataSource
import com.airongomes.moviedatabase.usecase.MovieUseCase
import com.airongomes.moviedatabase.usecase.MovieUseCaseImpl
import com.airongomes.moviedatabase.viewModel.DetailViewModel
import com.airongomes.moviedatabase.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val defaultModule = module {
    single { instantiateApi(TMDbApi::class.java) }
    single { RemoteDataSource(get()) }
    single { MovieRepository(get()) }
    factory<MovieUseCase> { MovieUseCaseImpl(get()) }

    viewModel { DetailViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}