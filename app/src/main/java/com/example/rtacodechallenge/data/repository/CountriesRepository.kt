package com.example.rtacodechallenge.data.repository

import com.example.rtacodechallenge.data.repository.remote.CountriesService
import com.example.rtacodechallenge.utils.Constants.Companion.REGIONAL_BLOCK

class CountriesRepository(private val countriesService: CountriesService) {

    suspend fun getCountries() = countriesService.getCountries(REGIONAL_BLOCK)
}