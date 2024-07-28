package org.discovr.mobile.movies.data.remote

import org.discovr.mobile.core.data.remote.models.PageRemote
import org.discovr.mobile.movies.data.remote.models.MovieSummaryRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRemoteService {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int): PageRemote<MovieSummaryRemote>
}