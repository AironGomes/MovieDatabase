package com.airongomes.moviedatabase.domain.repository

import com.airongomes.moviedatabase.domain.remote.api.TMDbApi

class RemoteDataSource(private val api: TMDbApi) {
    suspend fun getMovie(movieId: Int) = api.getMovie(movieId)
    suspend fun getMoviesInTheaters(page: Int) = api.getMoviesInTheaters(page)
    suspend fun getGenres() = api.getGenres()
}