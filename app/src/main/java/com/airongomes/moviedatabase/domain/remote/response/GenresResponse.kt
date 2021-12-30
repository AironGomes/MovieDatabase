package com.airongomes.moviedatabase.domain.remote.response

import com.airongomes.moviedatabase.domain.model.Genres
import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

fun List<GenresResponse>.toModel() = map { it.toModel() }

fun GenresResponse.toModel() = Genres(
    id = id,
    name = name
)
