package com.airongomes.moviedatabase.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.airongomes.moviedatabase.domain.model.Movie
import com.airongomes.moviedatabase.domain.repository.MovieRepository
import com.airongomes.moviedatabase.domain.source.MoviePagingSource

interface MovieUseCase {
    operator fun invoke(): Pager<Int, Movie>
}

class MovieUseCaseImpl(private val repository: MovieRepository) : MovieUseCase {

    override fun invoke(): Pager<Int, Movie> =
        Pager(
            PagingConfig(
                pageSize = MoviePagingSource.PAGE_SIZE
            )
        ) {
            MoviePagingSource(repository)
        }
}