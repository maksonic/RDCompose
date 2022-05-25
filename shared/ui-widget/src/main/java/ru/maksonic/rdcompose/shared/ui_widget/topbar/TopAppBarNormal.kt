package ru.maksonic.rdcompose.shared.ui_widget.topbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ru.maksonic.rdcompose.shared.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
fun TopAppBarNormal(
    title: String,
    backgroundColor: Color = RDTheme.color.background,
    backPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = RDTheme.typography.toolbarTitle,
                color = RDTheme.color.primaryText
            )
        },
        navigationIcon = {
            IconButton(onClick = { backPressed() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_arrow_back_24),
                    tint = RDTheme.color.controlNormal,
                    contentDescription = ""
                )
            }
        },
        backgroundColor = backgroundColor,
        elevation = RDTheme.elevation.elevationDisable
    )
}