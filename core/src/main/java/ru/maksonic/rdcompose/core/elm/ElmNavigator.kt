package ru.maksonic.rdcompose.core.elm

import androidx.navigation.NavHostController

/**
 * @Author maksonic on 23.05.2022
 */
abstract class ElmNavigator {
    lateinit var navController: NavHostController

    fun backPressed() = navController.popBackStack()

    fun navigate(destination: String) {
        if (destination != navController.currentDestination?.route)
            navController.navigate(destination)
        else return
    }

    fun getArgument(key: String): String =
        navController.currentBackStackEntry?.arguments?.getString(key) ?: ""
}
