package com.example.rtacodechallenge.di

import com.example.rtacodechallenge.data.repository.CountriesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CountriesRepository(get()) }
}