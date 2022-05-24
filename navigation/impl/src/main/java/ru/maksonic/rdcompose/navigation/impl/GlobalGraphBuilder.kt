package ru.maksonic.rdcompose.navigation.impl

import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * @Author maksonic on 23.05.2022
 */
interface GlobalGraphBuilder {
    fun globalNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        isDarkMode: State<Boolean>
    )
}