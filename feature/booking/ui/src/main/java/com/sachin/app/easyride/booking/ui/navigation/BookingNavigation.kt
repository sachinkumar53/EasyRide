package com.sachin.app.easyride.booking.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sachin.app.easyride.booking.ui.screen.home.HomeRoute

private const val BOOKING_ROUTE = "booking"
private const val HOME_ROUTE = "home"

fun NavController.navigateToBooking() {
    navigate(BOOKING_ROUTE)
}

fun NavGraphBuilder.bookingNavGraph() {
    navigation(
        startDestination = HOME_ROUTE,
        route = BOOKING_ROUTE
    ) {
        composable(HOME_ROUTE) {
            HomeRoute()
        }
    }
}