package org.discovr.mobile.movies.di

import org.discovr.mobile.movies.viewmodels.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
}