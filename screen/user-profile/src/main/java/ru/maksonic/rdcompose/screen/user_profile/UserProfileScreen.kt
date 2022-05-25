package ru.maksonic.rdcompose.screen.user_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.maksonic.rdcompose.shared.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.topbar.TopAppBarNormal

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
fun UserProfileScreen() {
    UserProfileScreenUi()
}

@Composable
private fun UserProfileScreenUi(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBarNormal(
                title = stringResource(R.string.scr_user_profile_toolbar_title),
                backPressed = {})
        },
        backgroundColor = RDTheme.color.background,
        modifier = modifier.systemBarsPadding()
    ) { padding ->
        Column(modifier.padding(padding)) {

        }
    }
}