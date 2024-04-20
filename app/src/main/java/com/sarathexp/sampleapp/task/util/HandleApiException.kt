package com.sarathexp.sampleapp.task.util

import com.sarathexp.sampleapp.task.domain.RequestError
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun handleApiError(e: Exception): RequestError.Network {
    return when (e) {
        is HttpException -> {
            when (e.code()) {
                408 -> RequestError.Network.TIMEOUT
                else -> RequestError.Network.UNKNOWN
            }
        }
        is SocketTimeoutException -> {
            RequestError.Network.NO_INTERNET
        }
        is UnknownHostException -> {
            RequestError.Network.NO_INTERNET
        }
        else -> {
            RequestError.Network.UNKNOWN
        }
    }
}
