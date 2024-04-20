package com.sarathexp.sampleapp.task.presentation.screen.login.interactor

import com.sarathexp.sampleapp.task.app.common.UIEvent

sealed interface LoginUIEvent : UIEvent {
    data object LoginSuccess : LoginUIEvent

    data class LoginError(val message: String) : LoginUIEvent
}
