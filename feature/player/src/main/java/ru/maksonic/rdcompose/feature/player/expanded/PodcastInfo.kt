package ru.maksonic.rdcompose.feature.player.expanded

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 02.06.2022
 */
@Composable
fun PodcastInfo(modifier: Modifier = Modifier) {
    val innerModifier = Modifier
    val dp32 = RDTheme.padding.dp32
    val scrollState = rememberScrollState()
    var shouldAnimated by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(shouldAnimated) {
        scrollState.animateScrollTo(
            scrollState.maxValue,
            animationSpec = tween(5000, 200, easing = CubicBezierEasing(0f, 0f, 0f, 0f))
        )
        scrollState.scrollTo(0)
        shouldAnimated = !shouldAnimated
    }
    Column(
        modifier
            .fillMaxWidth()
            .padding(start = dp32, end = dp32)
    ) {
        Text(
            text = "This is sample of very very very long podcast name",
            style = RDTheme.typography.toolbarTitle,
            color = RDTheme.color.primaryText,
            textAlign = TextAlign.Center,
            modifier = innerModifier
                .padding(bottom = RDTheme.padding.dp8)
                .horizontalScroll(scrollState, false)
        )
        Text(
            "Category name",
            style = RDTheme.typography.title,
            color = RDTheme.color.secondaryText,
            textAlign = TextAlign.Center,
            modifier = innerModifier.fillMaxWidth()
        )
    }
}

