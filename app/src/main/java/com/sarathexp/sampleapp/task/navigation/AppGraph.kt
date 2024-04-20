package com.sarathexp.sampleapp.task.navigation

import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.sarathexp.sampleapp.task.presentation.screen.destinations.HomeDestination
import com.sarathexp.sampleapp.task.presentation.screen.destinations.LoginDestination
import com.sarathexp.sampleapp.task.presentation.screen.destinations.MainDestination

object AppGraph {

    val auth =
        object : NavGraphSpec {
            override val route = "AUTH"
            override val startRoute = LoginDestination
            override val destinationsByRoute =
                listOf<DestinationSpec<*>>(LoginDestination).associateBy { it.route }
        }

    val home =
        object : NavGraphSpec {
            override val route = "HOME"
            override val startRoute = HomeDestination
            override val destinationsByRoute =
                listOf<DestinationSpec<*>>(
                        HomeDestination,
                    )
                    .associateBy { it.route }
        }

    // Root NavGraph
    val root =
        object : NavGraphSpec {
            override val route = "ROOT"
            override val startRoute = MainDestination
            override val destinationsByRoute =
                listOf<DestinationSpec<*>>(MainDestination).associateBy { it.route }
            override val nestedNavGraphs = listOf(auth, home)
        }
}
