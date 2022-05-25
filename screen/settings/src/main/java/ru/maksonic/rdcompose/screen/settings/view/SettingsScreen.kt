package ru.maksonic.rdcompose.screen.settings.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.maksonic.rdcompose.screen.settings.R
import ru.maksonic.rdcompose.screen.settings.view.item.*
import ru.maksonic.rdcompose.screen.settings.view.item.ItemAppTheme
import ru.maksonic.rdcompose.screen.settings.view.item.ItemApplication
import ru.maksonic.rdcompose.screen.settings.view.item.ItemCache
import ru.maksonic.rdcompose.screen.settings.view.item.ItemInfo
import ru.maksonic.rdcompose.screen.settings.view.item.ItemNotification
import ru.maksonic.rdcompose.shared.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R.*
import ru.maksonic.rdcompose.shared.ui_widget.button.PrimaryButton
import ru.maksonic.rdcompose.shared.ui_widget.topbar.TopAppBarNormal

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
fun SettingsScreen() {
    SettingsScreenUi()
}

@Composable
fun SettingsScreenUi(modifier: Modifier = Modifier) {
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
                backPressed = {}
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
                SignUpWidget()
            }
            ItemAppTheme()
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

@Composable
fun SignUpWidget(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .background(RDTheme.color.surface)
            .padding(bottom = RDTheme.padding.dp16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Войдите в профиль",
            style = RDTheme.typography.display,
            color = RDTheme.color.primaryText,
            modifier = modifier.padding(RDTheme.padding.dp8)
        )
        Text(
            "Возможность слушать и скачивать подкасты,\n+ 50% к физическим показателям. \uD83D\uDE01",
            style = RDTheme.typography.title,
            color = RDTheme.color.primaryText,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(RDTheme.padding.dp16)
        )
        PrimaryButton(action = {}, title = stringResource(id = string.btn_title_create_account))
    }
}