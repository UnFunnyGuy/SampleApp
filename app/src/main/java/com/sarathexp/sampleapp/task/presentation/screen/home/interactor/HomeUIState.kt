package com.sarathexp.sampleapp.task.presentation.screen.home.interactor

import com.sarathexp.sampleapp.task.app.common.UIState

data class HomeUIState(
    val isUsersLoading: Boolean,
    val isUsersError: Boolean,
) : UIState
