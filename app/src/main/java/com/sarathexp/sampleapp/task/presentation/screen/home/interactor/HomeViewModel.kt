package com.sarathexp.sampleapp.task.presentation.screen.home.interactor

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sarathexp.sampleapp.task.app.common.BaseViewModel
import com.sarathexp.sampleapp.task.domain.use_case.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userUseCases: UserUseCases) :
    BaseViewModel<HomeUIState, HomeUIEvent, HomeUIActionEvent>() {

    val pagedUsers = userUseCases.getUsers.performStreaming().cachedIn(viewModelScope)

    override fun initialState(): HomeUIState {
        return HomeUIState(isUsersLoading = false, isUsersError = false)
    }

    override fun onActionEvent(action: HomeUIActionEvent) {
        when (action) {
            else -> {
                // Do nothing
            }
        }
    }
}
