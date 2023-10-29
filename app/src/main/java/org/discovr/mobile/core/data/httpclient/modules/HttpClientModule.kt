package org.discovr.mobile.core.data.httpclient.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.discovr.mobile.core.data.httpclient.HttpClientConstants
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File
import java.util.Date
import java.util.concurrent.TimeUnit

fun httpClientModule() = module {

    single {
        OkHttpClient()
    }

    single {
        get<OkHttpClient>().newBuilder()
            .connectTimeout(HttpClientConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HttpClientConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            .cache(get())
    }

    single {  }

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

    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
    }
}