package com.sarathexp.sampleapp.task.presentation.screen.home.interactor

import com.sarathexp.sampleapp.task.app.common.UIEvent

sealed interface HomeUIEvent : UIEvent {
    data class Alert(val message: String) : HomeUIEvent
}
