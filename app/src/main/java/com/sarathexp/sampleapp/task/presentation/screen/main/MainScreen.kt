package com.sarathexp.sampleapp.task.presentation.screen.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sarathexp.sampleapp.task.app.common.collectEvent
import com.sarathexp.sampleapp.task.app.common.collectState
import com.sarathexp.sampleapp.task.navigation.AppGraph
import com.sarathexp.sampleapp.task.presentation.composable.ScreenWrapper
import com.sarathexp.sampleapp.task.presentation.screen.destinations.HomeDestination
import com.sarathexp.sampleapp.task.presentation.screen.destinations.LoginDestination
import com.sarathexp.sampleapp.task.presentation.screen.main.interactor.MainUIEvent
import com.sarathexp.sampleapp.task.presentation.screen.main.interactor.MainViewModel
import com.sarathexp.sampleapp.task.presentation.util.popAll

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val navHostEngine =
        rememberAnimatedNavHostEngine(rootDefaultAnimations = RootNavGraphDefaultAnimations())

    Scaffold(modifier = modifier) { padding ->
        DestinationsNavHost(
            modifier = Modifier.fillMaxSize(),
            engine = navHostEngine,
            navGraph = AppGraph.root
        )
    }
}

@Composable
@Destination
fun Main(navigator: DestinationsNavigator, viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.collectState()
    val uiEvent by viewModel.collectEvent()

    ScreenWrapper(Modifier.fillMaxSize()) {
        if (state.isLoading) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
                Text("Mock Loading..", modifier = Modifier.padding(top = 8.dp))
            }
        }
    }

    LaunchedEffect(uiEvent) {
        uiEvent?.let {
            when (it) {
                MainUIEvent.NavigateToHome -> navigator.popAll { HomeDestination }
                MainUIEvent.NavigateToLogin -> navigator.popAll { LoginDestination }
            }
        }
    }
}
