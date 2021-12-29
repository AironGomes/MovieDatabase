package com.airongomes.moviedatabase.domain.model

data class MovieList(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int
)

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val genreIds: List<Int>,
    val releaseDate: String
)
