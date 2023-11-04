package org.discovr.mobile.core.di

import android.content.Context
import org.discovr.mobile.core.di.module.coreModule
import org.discovr.mobile.movies.di.movieModules
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

object KoinApp {

    fun start(context: Context) {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(context)
            modules(
                listOf(coreModule) + movieModules
            )
        }
    }
}