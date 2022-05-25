package ru.maksonic.rdcompose.screen.main.view

import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.maksonic.rdcompose.screen.main.model.Msg
import ru.maksonic.rdcompose.shared.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R.drawable
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton

/**
 * @Author maksonic on 24.05.2022
 */
@Composable
internal fun MainTopAppBar(sendMsg: Message) {
    TopAppBar(
        title = {},
        backgroundColor = RDTheme.color.background,
        elevation = 0.dp,
        navigationIcon = {
            IconActionButton(onClick = { sendMsg(Msg.Ui.ShowUserProfile) }) {
                Icon(
                    painter = painterResource(id = drawable.ic_user_profile),
                    contentDescription = null,
                    tint = RDTheme.color.controlNormal
                )
            }
        },
        actions = {
            IconActionButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = drawable.ic_round_search),
                    contentDescription = null,
                    tint = RDTheme.color.controlNormal
                )
            }
            IconActionButton(onClick = { sendMsg(Msg.Ui.ShowSettings) }) {
                Icon(
                    painter = painterResource(id = drawable.ic_settings),
                    contentDescription = null,
                    tint = RDTheme.color.controlNormal
                )
            }
        }
    )
}