package com.sarathexp.sampleapp.task.domain

sealed interface Error

sealed interface RequestError : Error {
    enum class Network : RequestError {
        NO_INTERNET,
        TIMEOUT,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN
    }
}
