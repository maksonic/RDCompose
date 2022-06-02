package ru.maksonic.rdcompose.feature.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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