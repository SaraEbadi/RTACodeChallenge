package com.example.rtacodechallenge.di

import com.example.rtacodechallenge.feature.countries.CountriesViewModel
import com.example.rtacodechallenge.feature.countrieslist.CountryListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CountryListViewModel(get()) }
    viewModel { CountriesViewModel(get()) }
}