package dev.ryanle.sportsscoresandroid.util

sealed class Result<T>(val data: T? = null, val message: String? = null, val code: Int? = null) {
    class Success<T>(data: T?) : Result<T>(data)
    class Error<T>(message: String, code: Int? = null, data: T? = null) :
        Result<T>(data, message, code)
}