package com.airongomes.moviedatabase.domain.remote

import com.airongomes.moviedatabase.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T> instantiateApi(apiClass: Class<T>): T =
    Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        )
        .baseUrl(BuildConfig.BASE_URL)
        .build()
        .create(apiClass)