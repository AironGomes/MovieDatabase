package com.airongomes.moviedatabase.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.airongomes.moviedatabase.domain.model.Movie
import com.airongomes.moviedatabase.domain.source.MoviePagingSource
import com.airongomes.moviedatabase.domain.source.MoviePagingSource.Companion.PAGE_SIZE
import com.airongomes.moviedatabase.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val repository: Repository): ViewModel() {

    fun fetchMoviesPage(): Flow<PagingData<Movie>> =
        Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            MoviePagingSource(repository)
        }.flow.cachedIn(viewModelScope)
}