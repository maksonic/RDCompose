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

    fun getStringArgument(key: String): String =
        navController.currentBackStackEntry?.arguments?.getString(key) ?: ""

    fun getIntArgument(key: String): Int =
        navController.currentBackStackEntry?.arguments?.getInt(key) ?: 0

    fun getLongArgument(key: String): Long? =
        navController.currentBackStackEntry?.arguments?.getLong(key)
}
