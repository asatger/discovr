package org.discovr.mobile.movies.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.discovr.mobile.core.data.remote.mappers.toDomain
import org.discovr.mobile.core.data.util.networkBoundResource
import org.discovr.mobile.core.domain.Page
import org.discovr.mobile.core.domain.Resource
import org.discovr.mobile.movies.data.remote.MovieRemoteService
import org.discovr.mobile.movies.data.remote.mappers.toDomain
import org.discovr.mobile.movies.domain.MovieSummary

class MovieRepository(
    private val movieRemoteService: MovieRemoteService
) {
    private val nowPlayingMoviesCache: MutableMap<Int, Page<MovieSummary>> = mutableMapOf()

    fun getNowPlaying(page: Int): Flow<Resource<Page<MovieSummary>, Throwable>> =
        networkBoundResource(
            query = { flowOf(this.nowPlayingMoviesCache[page] ?: Page.empty(page)) },
            fetch = { this.movieRemoteService.getNowPlaying(page) },
            saveFetchResult = {
                this.nowPlayingMoviesCache[page] = it.toDomain { remote -> remote.toDomain() }
            },
            shouldFetch = { it?.results.isNullOrEmpty() }
        )
}