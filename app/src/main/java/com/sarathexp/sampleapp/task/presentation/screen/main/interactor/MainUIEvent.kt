package com.sarathexp.sampleapp.task.presentation.screen.main.interactor

import com.sarathexp.sampleapp.task.app.common.UIEvent

sealed interface MainUIEvent : UIEvent {
    data object NavigateToLogin : MainUIEvent

    data object NavigateToHome : MainUIEvent
}
