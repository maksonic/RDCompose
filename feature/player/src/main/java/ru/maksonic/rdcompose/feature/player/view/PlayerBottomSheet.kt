package ru.maksonic.rdcompose.feature.player.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.rdcompose.feature.player.view.PlayerCollapsed
import ru.maksonic.rdcompose.feature.player.view.PlayerExpanded

/**
 * @Author maksonic on 02.06.2022
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayerBottomSheet(scaffoldState: BottomSheetScaffoldState, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(0.98f)) {
        PlayerExpanded()
        PlayerCollapsed(scaffoldState = scaffoldState)
    }
}