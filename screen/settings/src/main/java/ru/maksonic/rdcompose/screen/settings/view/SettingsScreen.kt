package ru.maksonic.rdcompose.screen.settings.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.rdcompose.screen.settings.R
import ru.maksonic.rdcompose.screen.settings.model.Model
import ru.maksonic.rdcompose.screen.settings.model.Msg
import ru.maksonic.rdcompose.screen.settings.update.SettingsViewModel
import ru.maksonic.rdcompose.screen.settings.view.item.*
import ru.maksonic.rdcompose.screen.settings.widget.SignUpHeader
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.topbar.TopAppBarNormal

/**
 * @Author maksonic on 25.05.2022
 */
internal typealias Message = (Msg) -> Unit

@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg
    SettingsScreenUi(viewModel, model.value, sendMsg)
}

@Composable
private fun SettingsScreenUi(
    viewModel: SettingsViewModel,
    model: Model,
    sendMsg: Message,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val videosCheckedState = remember { mutableStateOf(false) }
    val newsCheckedState = remember { mutableStateOf(true) }
    val fakeUserAuth = remember { mutableStateOf(false) }
    val topBarColor = if (fakeUserAuth.value) RDTheme.color.background else RDTheme.color.surface

    Scaffold(
        topBar = {
            TopAppBarNormal(
                title = stringResource(R.string.scr_settings_toolbar_title),
                backgroundColor = topBarColor,
                backPressed = { viewModel.backPressed() }
            )
        },
        backgroundColor = RDTheme.color.background,
        modifier = modifier.systemBarsPadding()
    ) { padding ->
        Column(
            modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(state = scrollState)
        ) {
            if (!fakeUserAuth.value) {
                SignUpHeader()
            }
            ItemAppTheme(model, sendMsg)
            ItemNotification(videosCheckedState, newsCheckedState)
            ItemApplication()
            ItemCache()
            ItemInfo()
            Spacer(modifier.weight(1f))
            Spacer(modifier.height(RDTheme.padding.dp16))
            Footer(appVersion = "v0.0.1")
        }
    }
}