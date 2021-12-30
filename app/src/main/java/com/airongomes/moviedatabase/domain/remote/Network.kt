package com.airongomes.moviedatabase.domain.remote

import retrofit2.Response

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T> : NetworkResult<T>()
    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)
    class Success<T>(data: T) : NetworkResult<T>(data)
}

abstract class BaseApiResponse {
    suspend fun <RESPONSE, RESULT> safeApiCall(
        apiCall: suspend () -> Response<RESPONSE>,
        resultMapped: (RESPONSE) -> RESULT
    ): NetworkResult<RESULT> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    val result = resultMapped(body)
                    return NetworkResult.Success(result)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")
}