package com.airongomes.moviedatabase.domain.remote.response

import com.airongomes.moviedatabase.domain.model.Movie
import com.airongomes.moviedatabase.domain.model.MovieList
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieResponse>,
    @SerializedName("total_pages") val totalPages: Int
)

data class MovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("release_date") val releaseDate: String
)

fun MovieListResponse.toModel() = MovieList(
    page = page,
    results = results.toModel(),
    totalPages = totalPages
)

fun List<MovieResponse>.toModel() = map { it.toModel() }

fun MovieResponse.toModel() = Movie(
    id = id,
    title = title,
    posterPath = posterPath,
    genreIds = genreIds,
    releaseDate = releaseDate
)