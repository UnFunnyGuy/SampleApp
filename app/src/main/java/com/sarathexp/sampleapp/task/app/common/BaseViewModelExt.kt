package com.sarathexp.sampleapp.task.app.common

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun <S : UIState, VM : BaseViewModel<S, *, *>> VM.collectState() =
    uiState.collectAsStateWithLifecycle()

@Composable
fun <E : UIEvent, VM : BaseViewModel<*, E, *>> VM.collectEvent() =
    uiEvent.collectAsStateWithLifecycle(null)

fun BaseViewModel<*, *, *>.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    operation: suspend () -> Unit
): Job {
    return viewModelScope.launch(context = context, start = start) { operation() }
}
