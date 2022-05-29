package ru.maksonic.rdcompose.navigation.api

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import ru.maksonic.rdcompose.core.store.AppTheme

/**
 * @Author maksonic on 23.05.2022
 */
interface GraphBuilder {
    fun args(id: String) = "/{${id}}"
    fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
    )

    interface Categories {
        fun buildNavGraph(
            navGraphBuilder: NavGraphBuilder,
            nestedGraphs: NavGraphBuilder.() -> Unit,
            navController: NavHostController,
        )
    }
}