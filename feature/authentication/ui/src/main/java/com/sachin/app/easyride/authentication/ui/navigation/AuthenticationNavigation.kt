package com.sachin.app.easyride.authentication.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.sachin.app.easyride.authentication.ui.screen.LoginRoute
import com.sachin.app.easyride.authentication.ui.screen.OtpRoute

const val AUTHENTICATION_GRAPH_ROUTE = "authentication"
private const val LOGIN_ROUTE = "login"
private const val OTP_ROUTE = "otp"
internal const val PHONE_NUMBER_ARG = "phoneNumber"

internal fun NavController.navigateToOtp(
    phoneNumber: String,
    navOptions: NavOptions? = null
) {
    this.navigate("$OTP_ROUTE/$phoneNumber", navOptions)
}

fun NavGraphBuilder.authenticationNavGraph(
    navController: NavController
) {
    navigation(
        route = AUTHENTICATION_GRAPH_ROUTE,
        startDestination = LOGIN_ROUTE
    ) {
        composable(LOGIN_ROUTE) {
            LoginRoute(navController)
        }

        composable(
            route = "$OTP_ROUTE/{$PHONE_NUMBER_ARG}",
            arguments = listOf(navArgument(PHONE_NUMBER_ARG) { type = NavType.StringType })
        ) {
            OtpRoute()
        }
    }
}