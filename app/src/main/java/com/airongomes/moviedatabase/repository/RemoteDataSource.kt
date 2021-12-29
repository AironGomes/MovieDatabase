package com.airongomes.moviedatabase.repository

import com.airongomes.moviedatabase.domain.remote.api.TMDbApi

class RemoteDataSource(private val api: TMDbApi) {
//    suspend fun getMovie(movieId: Int) = api.getMovie(movieId)
    suspend fun getMoviesInTheaters() = api.getMoviesInTheaters()
    suspend fun getGenres() = api.getGenres()
}