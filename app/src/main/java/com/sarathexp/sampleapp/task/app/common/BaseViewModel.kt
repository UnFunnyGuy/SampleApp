package com.sarathexp.sampleapp.task.app.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

interface UIState

interface UIEvent

interface ActionEvent

abstract class BaseViewModel<State : UIState, Event : UIEvent, Action : ActionEvent>() :
    ViewModel() {

    private val initialState: State by lazy { initialState() }

    abstract fun initialState(): State

    abstract fun onActionEvent(action: Action)

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    private val _uiEventFlow = Channel<Event?>()
    val uiEvent = _uiEventFlow.receiveAsFlow()

    private val _uiActionEvent = Channel<Action>()
    private val uiActionEvent = _uiActionEvent.receiveAsFlow()

    init {
        collectActionEvent()
    }

    protected val currentState: State
        get() = uiState.value

    protected fun update(updatedState: State.() -> State) = _uiState.update(updatedState)

    protected fun sendOneTimeUIEvent(event: Event?) {
        // Not Ideal
        launch {
            _uiEventFlow.send(event)
            delay(15)
            _uiEventFlow.send(null)
        }
    }

    private fun collectActionEvent() {
        launch { uiActionEvent.collectLatest { onActionEvent(it) } }
    }
}
