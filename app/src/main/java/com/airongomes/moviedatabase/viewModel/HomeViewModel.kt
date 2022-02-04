package com.airongomes.moviedatabase.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.airongomes.moviedatabase.domain.model.Movie
import com.airongomes.moviedatabase.usecase.MovieUseCase
import kotlinx.coroutines.flow.Flow

class HomeViewModel(useCase: MovieUseCase) : ViewModel() {

    val moviePagingFlow: Flow<PagingData<Movie>> =
        useCase.fetchMoviePage().flow.cachedIn(viewModelScope)
}