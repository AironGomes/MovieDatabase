package com.airongomes.moviedatabase.repository

import com.airongomes.moviedatabase.domain.model.Genres
import com.airongomes.moviedatabase.domain.model.MovieList
import com.airongomes.moviedatabase.domain.remote.BaseApiResponse
import com.airongomes.moviedatabase.domain.remote.NetworkResult
import com.airongomes.moviedatabase.domain.remote.response.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseApiResponse() {

//    suspend fun getMovie(movieId: Int): Flow<NetworkResult<MovieDetail>> {
//        return flow<NetworkResult<MovieDetail>> {
//            emit(
//                safeApiCall(
//                    apiCall = { remoteDataSource.getMovie(movieId) },
//                    resultMapped = { it.toModel() }
//                )
//            )
//        }.flowOn(dispatcher)
//    }

    suspend fun getMoviesInTheaters(): Flow<NetworkResult<MovieList>> {
        return flow<NetworkResult<MovieList>> {
            emit(
                safeApiCall(
                    apiCall = { remoteDataSource.getMoviesInTheaters() },
                    resultMapped = { it.toModel() }
                )
            )

        }.flowOn(dispatcher)
    }

    suspend fun getGenres(): Flow<NetworkResult<List<Genres>>> {
        return flow<NetworkResult<List<Genres>>> {
            emit(
                safeApiCall(
                    apiCall = { remoteDataSource.getGenres() },
                    resultMapped = { list -> list.map { it.toModel() } }
                )
            )

        }.flowOn(dispatcher)
    }

}