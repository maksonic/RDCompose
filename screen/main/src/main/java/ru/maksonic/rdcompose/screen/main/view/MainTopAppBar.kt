package ru.maksonic.rdcompose.screen.main.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.Icon
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.maksonic.rdcompose.screen.main.model.Model
import ru.maksonic.rdcompose.screen.main.model.Msg
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R.drawable
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton

/**
 * @Author maksonic on 24.05.2022
 */
@Composable
internal fun MainTopAppBar(model: Model, sendMsg: Message) {

    AnimatedVisibility(
        visible = model.isShowTopBar,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        SmallTopAppBar(
            title = {},
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = RDTheme.color.background
            ),
            navigationIcon = {
                IconActionButton(
                    onClick = {
                        sendMsg(Msg.Ui.ShowUserProfile)
                    }
                ) {
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
}