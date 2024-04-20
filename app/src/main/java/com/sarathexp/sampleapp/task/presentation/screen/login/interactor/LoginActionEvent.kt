package com.sarathexp.sampleapp.task.presentation.screen.login.interactor

import com.sarathexp.sampleapp.task.app.common.ActionEvent

sealed interface LoginActionEvent : ActionEvent {
    data class UserNameChanged(val userName: String) : LoginActionEvent

    data class PasswordChanged(val password: String) : LoginActionEvent

    object Login : LoginActionEvent
}
