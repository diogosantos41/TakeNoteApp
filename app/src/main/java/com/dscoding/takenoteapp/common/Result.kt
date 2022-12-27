package com.dscoding.takenoteapp.common

sealed class Result<out T>(val data: T? = null, val errorMessage: StringResource? = null) {
    class Success<T>(data: T? = null) : Result<T>(data = data)
    class Error(errorMessage: StringResource) : Result<Nothing>(errorMessage = errorMessage)
}