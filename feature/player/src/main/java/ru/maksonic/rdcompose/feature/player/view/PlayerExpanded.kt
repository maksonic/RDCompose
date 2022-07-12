package ru.maksonic.rdcompose.feature.player.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.rdcompose.feature.player.view.expanded.*
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 01.06.2022
 */
@Composable
fun PlayerExpanded(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .background(RDTheme.color.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier.height(RDTheme.componentSize.playerCollapsedHeight))
        PodcastCard(modifier.weight(1f))
        Spacer(modifier.weight(0.1f))
        PodcastInfo()
        SliderPanel()
        CentralPlayer()
        Spacer(modifier.weight(0.1f))
        BottomPanel()
    }
}




