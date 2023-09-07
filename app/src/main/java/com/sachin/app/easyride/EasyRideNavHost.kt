package com.sachin.app.easyride

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sachin.app.easyride.authentication.ui.navigation.AUTHENTICATION_GRAPH_ROUTE
import com.sachin.app.easyride.authentication.ui.navigation.authenticationNavGraph

@Composable
fun EasyRideNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AUTHENTICATION_GRAPH_ROUTE
    ) {
        authenticationNavGraph(navController)
    }
}