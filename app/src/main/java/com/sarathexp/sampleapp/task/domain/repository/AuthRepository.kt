package com.sarathexp.sampleapp.task.domain.repository

import com.sarathexp.sampleapp.task.domain.RequestError
import com.sarathexp.sampleapp.task.domain.RequestResult

interface AuthRepository {
    suspend fun login(
        userName: String,
        password: String
    ): RequestResult<Boolean, RequestError.Network>

    suspend fun isLoggedIn(): Boolean
}
