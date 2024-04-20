package com.sarathexp.sampleapp.task.presentation.screen.login.interactor

import com.sarathexp.sampleapp.task.app.common.BaseViewModel
import com.sarathexp.sampleapp.task.app.common.launch
import com.sarathexp.sampleapp.task.domain.Error
import com.sarathexp.sampleapp.task.domain.LoginRequestValidator
import com.sarathexp.sampleapp.task.domain.RequestResult
import com.sarathexp.sampleapp.task.domain.model.LoginRequest
import com.sarathexp.sampleapp.task.domain.use_case.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases) :
    BaseViewModel<LoginUIState, LoginUIEvent, LoginActionEvent>() {

    private val loginRequestValidator by lazy { LoginRequestValidator() }

    override fun initialState(): LoginUIState {
        return LoginUIState(userName = "", password = "", isLoading = false)
    }

    override fun onActionEvent(action: LoginActionEvent) {
        when (action) {
            LoginActionEvent.Login -> login()
            is LoginActionEvent.PasswordChanged -> update { copy(password = action.password) }
            is LoginActionEvent.UserNameChanged -> update { copy(userName = action.userName) }
        }
    }

    private fun login() {
        val loginRequest =
            LoginRequest(userName = currentState.userName, password = currentState.password)
        val validationResult = loginRequestValidator.validate(request = loginRequest)

        when (validationResult) {
            is RequestResult.Error -> {
                handleValidationError(validationResult.error)
                return
            }
            is RequestResult.Success -> {
                update { copy(passWordError = null, userNameError = null) }
                // TODO: Call login use case
                launch {
                    update { copy(isLoading = true) }
                    val response = authUseCases.login.perform(loginRequest)
                    if (response is RequestResult.Error) {
                        sendOneTimeUIEvent(
                            LoginUIEvent.LoginError("Login failed, please try again")
                        )
                    } else {
                        sendOneTimeUIEvent(LoginUIEvent.LoginSuccess)
                    }
                    update { copy(isLoading = false) }
                }
            }
        }
    }

    private fun handleValidationError(error: Error) {
        if (error is LoginRequestValidator.PasswordError) {
            when (error) {
                LoginRequestValidator.PasswordError.EMPTY ->
                    update { copy(passWordError = "Password cannot be empty") }
                LoginRequestValidator.PasswordError.TOO_SHORT ->
                    update { copy(passWordError = "Password is too short") }
            }
        } else if (error is LoginRequestValidator.UsernameError) {
            when (error) {
                LoginRequestValidator.UsernameError.EMPTY ->
                    update { copy(userNameError = "Username cannot be empty") }
                LoginRequestValidator.UsernameError.TOO_SHORT ->
                    update { copy(userNameError = "Username is too short") }
            }
        }
    }
}
