package ru.maksonic.rdcompose.navigation.api

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * @Author maksonic on 23.05.2022
 */
interface GraphBuilder {
    fun args(id: String) = "/{${id}}"
    fun argsInt(id: Int) = "/{${id}}"

    @OptIn(ExperimentalMaterialApi::class)
    fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        playerBottomSheetState: BottomSheetScaffoldState
    )
}