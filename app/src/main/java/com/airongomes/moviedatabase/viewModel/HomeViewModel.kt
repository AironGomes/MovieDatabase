package com.airongomes.moviedatabase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airongomes.moviedatabase.domain.model.MovieList
import com.airongomes.moviedatabase.domain.remote.NetworkResult
import com.airongomes.moviedatabase.domain.remote.Service
import com.airongomes.moviedatabase.repository.RemoteDataSource
import com.airongomes.moviedatabase.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    //TODO: Dependency injection
    private val service = Service.retrofitService
    private val remoteDataSource = RemoteDataSource(service)
    private val repository: Repository = Repository(remoteDataSource)

    private val _response: MutableLiveData<NetworkResult<MovieList>> = MutableLiveData()
    val response: LiveData<NetworkResult<MovieList>> = _response

    fun fetchMovieList() = viewModelScope.launch {
        repository.getMoviesInTheaters().collect { movieList ->
            _response.postValue(movieList)
        }
    }

}