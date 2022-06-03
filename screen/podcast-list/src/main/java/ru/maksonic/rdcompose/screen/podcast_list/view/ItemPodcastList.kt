package ru.maksonic.rdcompose.screen.podcast_list.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.rdcompose.screen.podcast_list.model.Msg
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastUi
import ru.maksonic.rdcompose.shared.ui_widget.ImageWithShimmer
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton
import ru.maksonic.rdcompose.shared.ui_widget.button.rippleClickable

/**
 * @Author maksonic on 30.05.2022
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ItemPodcastList(
    sendMsg: Message,
    podcast: PodcastUi,
    playerSheet: BottomSheetScaffoldState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    Row(
        modifier
            .fillMaxWidth()
            .height(RDTheme.componentSize.itemNormalListHeight)
            .rippleClickable(rippleColor = RDTheme.color.primary) {
                sendMsg(Msg.Ui.OnPodcastClicked(scope, playerSheet))
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Card(
            modifier.padding(
                start = RDTheme.padding.dp16,
                top = RDTheme.padding.dp8,
                bottom = RDTheme.padding.dp8
            ),
            backgroundColor = RDTheme.color.surface,
            shape = RDTheme.shape.cornerSmall,
            elevation = RDTheme.elevation.elevationDisable
        ) {
            ImageWithShimmer(podcast.image, modifier.aspectRatio(1f))
        }

        Text(
            text = podcast.name,
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
                painterResource(id = R.drawable.ic_more_vertical),
                tint = RDTheme.color.controlNormal,
                contentDescription = "",
            )
        }
    }
}