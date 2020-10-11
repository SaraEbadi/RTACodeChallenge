package com.example.rtacodechallenge.utils

sealed class UseCaseResponse<T>(
    val data: T? = null,
    val message: String? = null) {
    class Success<T : Any>(data: T) : UseCaseResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : UseCaseResponse<T>(data, message)
}