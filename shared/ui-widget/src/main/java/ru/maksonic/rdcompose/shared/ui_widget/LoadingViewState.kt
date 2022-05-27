package ru.maksonic.rdcompose.shared.ui_widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 26.05.2022
 */
@Composable
fun LoadingViewState(modifier: Modifier = Modifier) {
    Box(
        modifier.fillMaxSize().background(RDTheme.color.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier.size(RDTheme.componentSize.circularProgressIndicatorSize),
            color = RDTheme.color.primary,
            strokeWidth = RDTheme.componentSize.circularProgressIndicatorStrokeWidth
        )

        Icon(
            painterResource(R.drawable.ic_rd_filled),
            tint = RDTheme.color.primary,
            modifier = modifier.size(50.dp),
            contentDescription = ""
        )
    }
}