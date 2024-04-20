package com.sarathexp.sampleapp.task.domain.use_case.auth

import com.sarathexp.sampleapp.task.app.common.BaseUseCase
import com.sarathexp.sampleapp.task.domain.repository.AuthRepository

class CheckLogin(private val authRepository: AuthRepository) : BaseUseCase<Nothing, Boolean> {
    override suspend fun perform(): Boolean {
        return authRepository.isLoggedIn()
    }
}
