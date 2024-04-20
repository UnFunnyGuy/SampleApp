package com.sarathexp.sampleapp.task.presentation.screen.login.interactor

import com.sarathexp.sampleapp.task.app.common.UIState

data class LoginUIState(
    val userName: String,
    val password: String,
    val isLoading: Boolean,
    val passWordError: String? = null,
    val userNameError: String? = null
) : UIState
