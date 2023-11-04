package org.discovr.mobile.movies.di

import org.discovr.mobile.movies.data.remote.MovieRemoteService
import org.koin.dsl.module
import retrofit2.Retrofit

internal val remoteServiceModule = module {

    factory {
        get<Retrofit>().create(MovieRemoteService::class.java)
    }
}