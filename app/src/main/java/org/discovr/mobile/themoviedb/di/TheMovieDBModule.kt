package org.discovr.mobile.themoviedb.di

import TheMovieDBImageLoader
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.discovr.mobile.core.utils.ImageLoader
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.URL
import java.util.Date

val theMovieDBModule = module {

    single<ImageLoader> {
        TheMovieDBImageLoader(URL("https://image.tmdb.org/t/p/"))
    }

    single<Retrofit> {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}