package ru.maksonic.rdcompose.shared.ui_widget.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
fun TopAppBarNormal(
    modifier: Modifier = Modifier,
    title: String = "",
    backgroundColor: Color = RDTheme.color.background,
    elevation: Dp = RDTheme.elevation.elevationDisable,
    titleVisibilityState: Boolean = false,
    bgAlpha: Float = 1f,
    backPressed: () -> Unit
) {
    val alpha = animateFloatAsState(targetValue = bgAlpha, animationSpec = tween(350))
    TopAppBar(
        title = {
            AnimatedVisibility(
                visible = titleVisibilityState,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = title,
                    style = RDTheme.typography.toolbarTitle,
                    color = RDTheme.color.primaryText
                )
            }
        },
        navigationIcon = {
            IconActionButton(onClick = { backPressed() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_arrow_back_24),
                    tint = RDTheme.color.controlNormal,
                    contentDescription = ""
                )
            }
        },
        backgroundColor = backgroundColor.copy(alpha = alpha.value),
        elevation = elevation,
        modifier = modifier
    )
}