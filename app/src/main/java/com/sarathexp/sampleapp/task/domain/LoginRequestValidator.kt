package com.sarathexp.sampleapp.task.domain

import com.sarathexp.sampleapp.task.domain.model.LoginRequest

class LoginRequestValidator {

    fun validate(request: LoginRequest): RequestResult<Unit, Error> {
        // Not handling multiple validation errors
        val passwordValidationResult = validatePassword(request.password)
        if (passwordValidationResult is RequestResult.Error) {
            return RequestResult.Error(passwordValidationResult.error)
        }

        val usernameValidationResult = validateUsername(request.userName)
        if (usernameValidationResult is RequestResult.Error) {
            return RequestResult.Error(usernameValidationResult.error)
        }

        return RequestResult.Success(Unit)
    }

    private fun validatePassword(password: String): RequestResult<Unit, PasswordError> {
        if (password.isEmpty()) {
            return RequestResult.Error(PasswordError.EMPTY)
        }
        if (password.length < 5) {
            return RequestResult.Error(PasswordError.TOO_SHORT)
        }

        return RequestResult.Success(Unit)
    }

    fun validateUsername(username: String): RequestResult<Unit, UsernameError> {

        if (username.isEmpty()) {
            return RequestResult.Error(UsernameError.EMPTY)
        }

        if (username.length < 3) {
            return RequestResult.Error(UsernameError.TOO_SHORT)
        }

        return RequestResult.Success(Unit)
    }

    enum class PasswordError : Error {
        EMPTY,
        TOO_SHORT
    }

    enum class UsernameError : Error {
        EMPTY,
        TOO_SHORT
    }
}
