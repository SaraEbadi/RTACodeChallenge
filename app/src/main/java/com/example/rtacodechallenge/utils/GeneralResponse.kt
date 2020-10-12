package com.example.rtacodechallenge.utils

sealed class GeneralResponse<T>(
    val data: T? = null,
    val message: String? = null) {
    class Success<T>(data: T) : GeneralResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : GeneralResponse<T>(data, message)
    class Loading<T>(data: T? = null) : GeneralResponse<T>(data)
}