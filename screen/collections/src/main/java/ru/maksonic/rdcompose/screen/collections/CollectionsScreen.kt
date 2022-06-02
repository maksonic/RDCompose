package ru.maksonic.rdcompose.screen.collections

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.maksonic.rdcompose.core.utils.PlayerBackPressed
import ru.maksonic.rdcompose.navigation.api.R
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.ScreenTitleDisplay

/**
 * @Author maksonic on 25.05.2022
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CollectionsScreen(playerBottomSheetState: BottomSheetScaffoldState) {
    CollectionsScreenUi(playerBottomSheetState)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CollectionsScreenUi(
    playerBottomSheetState: BottomSheetScaffoldState,
    modifier: Modifier = Modifier
) {
    PlayerBackPressed(playerBottomSheetState)

    Scaffold(
        backgroundColor = RDTheme.color.background,
        modifier = modifier.padding(top = RDTheme.componentSize.smallTopBarHeight)
    ) { padding ->
        LazyColumn(modifier.padding(padding)) {
            item { ScreenTitleDisplay(title = stringResource(id = R.string.scr_collections)) }
        }
    }
}