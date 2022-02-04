package com.airongomes.moviedatabase.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.airongomes.moviedatabase.domain.model.Movie
import com.airongomes.moviedatabase.domain.repository.MovieRepository
import com.airongomes.moviedatabase.domain.source.MoviePagingSource

class MovieUseCase(private val repository: MovieRepository) {

    fun fetchMoviePage(): Pager<Int, Movie> =
        Pager(PagingConfig(pageSize = MoviePagingSource.PAGE_SIZE)) {
            MoviePagingSource(repository)
        }
}