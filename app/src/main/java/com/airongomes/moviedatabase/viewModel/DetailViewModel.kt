package com.airongomes.moviedatabase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airongomes.moviedatabase.domain.model.MovieDetail
import com.airongomes.moviedatabase.domain.remote.NetworkResult
import com.airongomes.moviedatabase.domain.remote.Service
import com.airongomes.moviedatabase.repository.RemoteDataSource
import com.airongomes.moviedatabase.repository.Repository
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    //TODO: Dependency injection
    private val service = Service.retrofitService
    private val remoteDataSource = RemoteDataSource(service)
    private val repository: Repository = Repository(remoteDataSource)


    private val _response: MutableLiveData<NetworkResult<MovieDetail>> = MutableLiveData()
    val response: LiveData<NetworkResult<MovieDetail>> = _response


    fun fetchMovieDetail(movieId: Int) = viewModelScope.launch {
        repository.getMovie(movieId).collect { movie ->
            _response.postValue(movie)
        }
    }

}