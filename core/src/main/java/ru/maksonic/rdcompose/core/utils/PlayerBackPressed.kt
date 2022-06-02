package ru.maksonic.rdcompose.core.utils

import androidx.activity.compose.BackHandler
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

/**
 * @Author maksonic on 02.06.2022
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayerBackPressed(state: BottomSheetScaffoldState) {
    val scope = rememberCoroutineScope()

    BackHandler(enabled = state.bottomSheetState.isExpanded) {
        scope.launch {
            state.bottomSheetState.animateTo(BottomSheetValue.Collapsed)
        }
    }
}