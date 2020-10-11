package com.example.rtacodechallenge.di

import com.example.rtacodechallenge.BuildConfig

val appComponent = listOf(
    viewModelModule,
    repositoryModule,
    moshiConverterModule,
    moshiModule,
    networkModule(BuildConfig.BASE_URL)
)