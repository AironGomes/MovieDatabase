package com.airongomes.moviedatabase.domain.remote.response


//data class MovieDetailResponse(
//    @SerializedName("id") val id: Int,
//    @SerializedName("title") val title: String,
//    @SerializedName("poster_path") val posterPath: String,
//    @SerializedName("genres") val genres: List<GenresResponse>,
//    @SerializedName("overview") val overview: String,
//    @SerializedName("release_date") val releaseDate: String,
//    @SerializedName("original_language") val originalLanguage: String
//)
//
//data class GenresResponse(
//    @SerializedName("id") val id: Int,
//    @SerializedName("name") val name: String
//)
//
//fun List<GenresResponse>.toModel() = map { it.toModel() }
//
//fun GenresResponse.toModel() = Genres(
//    id = id,
//    name = name
//)
//
//fun MovieDetailResponse.toModel() = MovieDetail(
//    id = id,
//    title = title,
//    posterPath = posterPath,
//    genres = genres.toModel(),
//    overview = overview,
//    releaseDate = releaseDate,
//    originalLanguage = originalLanguage
//)