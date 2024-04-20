package com.sarathexp.sampleapp.task.domain

typealias MyError = Error

sealed interface RequestResult<out D, out E : MyError> {
    data class Success<out D, out E : MyError>(val data: D) : RequestResult<D, E>

    data class Error<out D, out E : MyError>(val error: E) : RequestResult<D, E>
}
