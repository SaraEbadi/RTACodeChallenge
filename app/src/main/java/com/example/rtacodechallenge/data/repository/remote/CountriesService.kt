package com.example.rtacodechallenge.data.repository.remote

import com.example.rtacodechallenge.data.model.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesService {

    @GET("rest/v2/regionalbloc/{regionalbloc}")
    suspend fun getCountries(@Path("regionalbloc") regionalBlock: String): Response<List<Country>>
}