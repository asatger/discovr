package org.discovr.mobile.movies.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieRemote(
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "overview") val overview: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "id") val id: Int,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "title") val title: String,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "budget") val budget: Int,
    @Json(name = "homepage") val homepage: String,
    @Json(name = "imdb_id") val imdbId: String,
    @Json(name = "revenue") val revenue: Int,
    @Json(name = "runtime") val runtime: Int,
    @Json(name = "status") val status: String,
    @Json(name = "tagline") val tagline: String
)
