package com.airongomes.moviedatabase.domain.remote

import com.airongomes.moviedatabase.BuildConfig
import com.airongomes.moviedatabase.domain.remote.api.TMDbApi
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        GsonConverterFactory.create(
            GsonBuilder().create()
        )
    )
    .baseUrl(BuildConfig.BASE_URL)
    .build()

object Service {
    val retrofitService: TMDbApi by lazy {
        retrofit.create(TMDbApi::class.java)
    }
}