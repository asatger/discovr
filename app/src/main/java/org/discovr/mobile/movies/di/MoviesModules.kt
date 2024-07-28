package org.discovr.mobile.movies.di

import org.discovr.mobile.themoviedb.di.theMovieDBModule

val moviesModules = listOf(
    theMovieDBModule,
    remoteServiceModule,
    repositoryModule,
    viewModelModule
)