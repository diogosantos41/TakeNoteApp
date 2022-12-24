package com.dscoding.takenoteapp.common

sealed class Result<out T>(val data: T? = null, val failure: Failure? = null) {
    class Success<T>(data: T? = null) : Result<T>(data = data)
    class Error(failure: Failure) : Result<Nothing>(failure = failure)
}