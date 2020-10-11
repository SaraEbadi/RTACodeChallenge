package com.example.rtacodechallenge.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class Country(
    val name: String,
    var isAdded: Boolean = false
)

@Parcelize
@JsonClass(generateAdapter = true)
data class CountriesList(val countriesList: List<String>) : Parcelable