package com.airongomes.moviedatabase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airongomes.moviedatabase.domain.model.MovieDetail
import com.airongomes.moviedatabase.domain.remote.NetworkResult
import com.airongomes.moviedatabase.domain.repository.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MovieRepository): ViewModel() {

    private val _response: MutableLiveData<NetworkResult<MovieDetail>> = MutableLiveData()
    val response: LiveData<NetworkResult<MovieDetail>> = _response

    fun fetchMovieDetail(movieId: Int) = viewModelScope.launch {
        repository.getMovie(movieId).collect { movie ->
            _response.postValue(movie)
        }
    }

}