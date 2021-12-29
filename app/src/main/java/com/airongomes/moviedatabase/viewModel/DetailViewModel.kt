package com.airongomes.moviedatabase.viewModel

import androidx.lifecycle.ViewModel
import com.airongomes.moviedatabase.domain.remote.Service
import com.airongomes.moviedatabase.repository.RemoteDataSource
import com.airongomes.moviedatabase.repository.Repository

class DetailViewModel: ViewModel() {

    private val service = Service.retrofitService
    private val remoteDataSource = RemoteDataSource(service)
    private val repository: Repository = Repository(remoteDataSource)


//    private val _response: MutableLiveData<NetworkResult<MovieDetail>> = MutableLiveData()
//    val response: LiveData<NetworkResult<MovieDetail>> = _response
//
//
//    fun fetchMovieDetail(movieId: Int) = viewModelScope.launch {
//        repository.getMovie(movieId).collect { movie ->
//            _response.postValue(movie)
//        }
//    }

}