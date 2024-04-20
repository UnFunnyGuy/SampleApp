package com.sarathexp.sampleapp.task.domain.use_case.auth

import com.sarathexp.sampleapp.task.app.common.BaseUseCase
import com.sarathexp.sampleapp.task.domain.RequestError
import com.sarathexp.sampleapp.task.domain.RequestResult
import com.sarathexp.sampleapp.task.domain.model.LoginRequest
import com.sarathexp.sampleapp.task.domain.repository.AuthRepository

class Login(private val authRepository: AuthRepository) :
    BaseUseCase<LoginRequest, RequestResult<Boolean, RequestError.Network>> {
    override suspend fun perform(
        params: LoginRequest
    ): RequestResult<Boolean, RequestError.Network> {
        return authRepository.login(userName = params.userName, password = params.password)
    }
}
