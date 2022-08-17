package com.ali.aamer.mynotes.core.utils

sealed class Resource<T>(val status: Status, val data: T? = null, val message: String? = null) {
    class Success<T>(status: Status, data: T?) : Resource<T>(status = status, data = data)
    class Failure<T>(status: Status, message: String) :
        Resource<T>(status = status, message = message)

    class Loading<T>(status: Status) : Resource<T>(status = status)
}


enum class Status {
    SUCCESS, FAILURE, LOADING
}