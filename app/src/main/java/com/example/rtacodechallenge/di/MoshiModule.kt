package com.example.rtacodechallenge.di

import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Represents instance of MoshiConverterFactory.
 */
val moshiConverterModule = module {
    single {
        MoshiConverterFactory.create(get())
    }
}

/**
 * Represents builder of Moshi.
 */
val moshiModule = module {
    single {
        Moshi.Builder().build()
    }
}