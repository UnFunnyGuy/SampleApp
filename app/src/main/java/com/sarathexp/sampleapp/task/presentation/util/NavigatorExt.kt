package com.sarathexp.sampleapp.task.presentation.util

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.spec.Direction
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.sarathexp.sampleapp.task.navigation.AppGraph

fun DestinationsNavigator.popAll(
    navGraph: NavGraphSpec = AppGraph.root,
    destination: () -> Direction
) = this.navigate(destination()) { popUpTo(navGraph) { inclusive = true } }
