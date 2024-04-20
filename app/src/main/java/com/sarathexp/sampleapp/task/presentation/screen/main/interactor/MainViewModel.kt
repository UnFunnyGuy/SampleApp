package com.sarathexp.sampleapp.task.presentation.screen.main.interactor

import com.sarathexp.sampleapp.task.app.common.ActionEvent
import com.sarathexp.sampleapp.task.app.common.BaseViewModel
import com.sarathexp.sampleapp.task.app.common.launch
import com.sarathexp.sampleapp.task.domain.use_case.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val authUseCases: AuthUseCases) :
    BaseViewModel<MainUIState, MainUIEvent, MainActionEvent>() {

    init {
        checkLogin()
    }

    override fun initialState(): MainUIState {
        return MainUIState(isLoading = false)
    }

    override fun onActionEvent(action: MainActionEvent) {
        when (action) {
            else -> {}
        }
    }

    private fun checkLogin() {
        update { copy(isLoading = true) }
        launch {
            val isLoggedIn = authUseCases.checkLogin.perform()
            // Simulating a delay
            delay(1000)
            if (isLoggedIn) {
                sendOneTimeUIEvent(MainUIEvent.NavigateToHome)
            } else {
                sendOneTimeUIEvent(MainUIEvent.NavigateToLogin)
            }
            update { copy(isLoading = false) }
        }
    }
}

sealed interface MainActionEvent : ActionEvent
