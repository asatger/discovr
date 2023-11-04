package org.discovr.mobile.movies.di

import org.discovr.mobile.movies.data.repository.MovieRepository
import org.koin.dsl.module

internal val repositoryModule = module {

    factory {
        MovieRepository(get())
    }
}