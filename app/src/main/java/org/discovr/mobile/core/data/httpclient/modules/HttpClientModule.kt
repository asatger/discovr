package org.discovr.mobile.core.data.httpclient.modules

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.discovr.mobile.core.data.httpclient.HttpClientConstants
import org.discovr.mobile.themoviedb.utils.ApiKeyInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File
import java.util.concurrent.TimeUnit

internal val httpClientModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(HttpClientConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HttpClientConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(ApiKeyInterceptor())
            .cache(get())
            .build()
    }

    single {
        Cache(
            File(androidContext().cacheDir, HttpClientConstants.CACHE_DIR_NAME),
            HttpClientConstants.CACHE_MAX_SIZE
        )
    }

    single {
        HttpLoggingInterceptor().apply {
            this.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}