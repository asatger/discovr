package org.discovr.mobile.home.di

import org.discovr.mobile.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val homeViewModelModule = module {
    viewModel { HomeViewModel() }
}