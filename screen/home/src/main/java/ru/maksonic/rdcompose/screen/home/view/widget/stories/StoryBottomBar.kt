package ru.maksonic.rdcompose.screen.home.view.widget.stories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.IconCircleAction

/**
 * @Author maksonic on 08.06.2022
 */
@Composable
fun StoryBottomBar(modifier: Modifier = Modifier) {
    val isFavorite = remember { mutableStateOf(false) }
    val favoriteIcon =
        if (isFavorite.value) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outlined
    val favoriteColor =
        if (isFavorite.value) RDTheme.color.isFavorite else RDTheme.color.controlNormal

    Row(
        modifier
            .fillMaxWidth()
            .height(RDTheme.componentSize.bottomBarStoryHeight)
            .background(RDTheme.color.background.copy(alpha = 0.3f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier.weight(1f))

        IconCircleAction(
            icId = R.drawable.ic_share,
            action = {},
            modifier = modifier.padding(end = RDTheme.padding.dp8)
        )

        IconCircleAction(
            icId = favoriteIcon,
            tint = favoriteColor,
            action = { isFavorite.value = !isFavorite.value == true },
            modifier = modifier.padding(end = RDTheme.padding.dp8)
        )
    }
}