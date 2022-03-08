package com.airongomes.moviedatabase.domain.remote.api

import com.airongomes.moviedatabase.BuildConfig
import com.airongomes.moviedatabase.domain.remote.response.GenresResponse
import com.airongomes.moviedatabase.domain.remote.response.MovieDetailResponse
import com.airongomes.moviedatabase.domain.remote.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDbApi {

    @GET("/3/movie/{movieId}?api_key=${BuildConfig.API_KEY}&language=en-US")
    suspend fun getMovie(@Path("movieId") movieId: Int): Response<MovieDetailResponse>

    @GET("/3/movie/now_playing?api_key=${BuildConfig.API_KEY}&language=en-US")
    suspend fun getMoviesInTheaters(@Query("page") page: Int): Response<MovieListResponse>

    @GET("/3/genre/movie/list?api_key=${BuildConfig.API_KEY}&language=en-US")
    suspend fun getGenres(): Response<List<GenresResponse>>
}