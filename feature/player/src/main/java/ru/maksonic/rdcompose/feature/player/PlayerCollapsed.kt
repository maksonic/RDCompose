package ru.maksonic.rdcompose.feature.player

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.maksonic.rdcompose.feature.player.utils.currentFraction
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.ImageWithShimmer
import ru.maksonic.rdcompose.shared.ui_widget.IndicatorBottomSheet
import ru.maksonic.rdcompose.shared.ui_widget.R.drawable
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton
import ru.maksonic.rdcompose.shared.ui_widget.button.noRippleClickable

/**
 * @Author maksonic on 01.06.2022
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayerCollapsed(modifier: Modifier = Modifier, scaffoldState: BottomSheetScaffoldState) {
    val scope = rememberCoroutineScope()
    val hidePlayerAlpha = animateFloatAsState(1f - scaffoldState.currentFraction * 3)
    val showIndicatorAlpha = animateFloatAsState(0f + scaffoldState.currentFraction * 3)

    Box(modifier.background(RDTheme.color.surface)) {
        Row(
            modifier
                .fillMaxWidth()
                .height(RDTheme.componentSize.playerCollapsedHeight)
                .background(RDTheme.color.surface)
                .graphicsLayer(alpha = hidePlayerAlpha.value)
                .noRippleClickable { scope.launch { scaffoldState.bottomSheetState.expand() } },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                modifier.padding(
                    start = RDTheme.padding.dp16,
                    top = RDTheme.padding.dp4,
                    bottom = RDTheme.padding.dp4
                ),
                backgroundColor = RDTheme.color.surface,
                shape = RDTheme.shape.cornerSmall,
                elevation = RDTheme.elevation.elevationDisable
            ) {
                ImageWithShimmer("", modifier.aspectRatio(1f))
            }

            Text(
                text = "This is sample of very very very long podcast name",
                style = RDTheme.typography.body,
                color = RDTheme.color.primaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .weight(1f)
                    .padding(start = RDTheme.padding.dp8, end = RDTheme.padding.dp8)
            )

            IconActionButton(onClick = {}, modifier.padding(end = RDTheme.padding.dp8)) {
                Icon(
                    painterResource(id = drawable.ic_play_rounded),
                    tint = RDTheme.color.controlNormal,
                    contentDescription = "",
                    modifier = modifier.size(40.dp)
                )
            }
        }

        Row(
            modifier
                .fillMaxWidth()
                .height(RDTheme.componentSize.playerCollapsedHeight)
                .graphicsLayer(alpha = showIndicatorAlpha.value),
            horizontalArrangement = Arrangement.Center,
        ) {
            IndicatorBottomSheet()
        }
    }
}