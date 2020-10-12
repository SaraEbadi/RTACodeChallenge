package com.example.rtacodechallenge.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    val name: String,
    var isAdd: Boolean = false
)

@JsonClass(generateAdapter = true)
data class CountriesList(val countriesList: List<String>)