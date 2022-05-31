package ru.maksonic.rdcompose.navigation.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * @Author maksonic on 23.05.2022
 */
interface GraphBuilder {
    fun args(id: String) = "/{${id}}"
    fun argsInt(id: Int) = "/{${id}}"
    fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
    )
}