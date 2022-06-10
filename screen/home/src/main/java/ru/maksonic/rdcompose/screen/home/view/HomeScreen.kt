package ru.maksonic.rdcompose.screen.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.rdcompose.core.utils.PlayerBackPressed
import ru.maksonic.rdcompose.screen.home.view.story.StoryDialog
import ru.maksonic.rdcompose.navigation.api.R
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.screen.home.update.HomeViewModel
import ru.maksonic.rdcompose.screen.home.view.widget.AudioStoryWidget
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R.*
import ru.maksonic.rdcompose.shared.ui_widget.ScreenTitleDisplay
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton
import ru.maksonic.rdcompose.shared.ui_widget.button.IconDefault

/**
 * @Author maksonic on 25.05.2022
 */
internal typealias Message = (Msg) -> Unit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(playerBottomSheetState: BottomSheetScaffoldState) {
    val viewModel: HomeViewModel = hiltViewModel()
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg

    HomeScreenUi(playerBottomSheetState, model.value, sendMsg)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenUi(
    playerBottomSheetState: BottomSheetScaffoldState,
    model: Model,
    sendMsg: Message,
    modifier: Modifier = Modifier
) {
    PlayerBackPressed(playerBottomSheetState)

    Scaffold(
        backgroundColor = RDTheme.color.background,
        modifier = modifier.padding(top = RDTheme.componentSize.smallTopBarHeight)
    ) { padding ->
        StoryDialog(model, sendMsg)

        LazyColumn(modifier.padding(padding)) {
            item { ScreenTitleDisplay(title = stringResource(id = R.string.scr_home)) }
            item { AudioStoryWidget(model, sendMsg) }
        }
    }
}