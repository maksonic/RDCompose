package ru.maksonic.rdcompose.screen.podcast_list.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.maksonic.rdcompose.screen.podcast_list.model.Model
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.ImageWithShimmer
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton
import ru.maksonic.rdcompose.shared.ui_widget.button.OutlinedSecondaryButton

/**
 * @Author maksonic on 30.05.2022
 */
@Composable
internal fun CategoryHeader(model: Model, modifier: Modifier = Modifier) {
    val dp16 = RDTheme.padding.dp16
    val dp8 = RDTheme.padding.dp8
    val resources = LocalContext.current.resources
    val podcastsInCategoryCount = resources.getQuantityString(
        ru.maksonic.rdcompose.screen.podcast_list.R.plurals.podcast_count_hint,
        model.podcasts.count(), model.podcasts.count()
    )
    val innerModifier = Modifier
    Column(
        modifier
            .fillMaxWidth()
            .padding(top = dp16)
    ) {
        Row(
            innerModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                innerModifier
                    .fillMaxSize(0.5f)
                    .padding(start = dp16, end = dp8),
                shape = RDTheme.shape.cornerNormal,
                backgroundColor = RDTheme.color.surface
            ) {
                ImageWithShimmer(model.categoryInfo.categoryImage)
            }

            Column(
                innerModifier
                    .fillMaxSize()
                    .padding(start = dp8, end = dp16)
            ) {
                Text(
                    text = model.categoryInfo.name,
                    style = RDTheme.typography.header,
                    color = RDTheme.color.primaryText
                )

                Text(
                    text = podcastsInCategoryCount,
                    style = RDTheme.typography.caption,
                    color = RDTheme.color.secondaryText,
                    modifier = innerModifier.padding(top = dp8)
                )

                Row(
                    innerModifier
                        .fillMaxWidth()
                        .padding(start = dp16, end = dp16, top = dp8),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconActionButton(onClick = {}) {
                        Icon(
                            painterResource(id = R.drawable.ic_download),
                            tint = RDTheme.color.controlNormal,
                            contentDescription = ""
                        )
                    }
                    IconActionButton(onClick = {}) {
                        Icon(
                            painterResource(id = R.drawable.ic_round_playlist_add),
                            tint = RDTheme.color.controlNormal,
                            contentDescription = ""
                        )
                    }
                    IconActionButton(onClick = {}) {
                        Icon(
                            painterResource(id = R.drawable.ic_share),
                            tint = RDTheme.color.controlNormal,
                            contentDescription = ""
                        )
                    }
                }
            }
        }
        Row(
            innerModifier
                .fillMaxWidth()
                .padding(start = dp16, end = dp16, top = dp16, bottom = dp8)
        ) {
            OutlinedSecondaryButton(
                onClick = {},
                iconId = R.drawable.ic_round_shuffle,
                title = stringResource(R.string.txt_player_shuffle),
                modifier = innerModifier.weight(1f)
            )
            Spacer(innerModifier.width(dp16))
            OutlinedSecondaryButton(
                onClick = {},
                backgroundColor = RDTheme.color.primary,
                onButtonColor = RDTheme.color.onPrimary,
                rippleColor = RDTheme.color.onPrimary,
                iconId = R.drawable.ic_play_rounded,
                title = stringResource(R.string.txt_player_play),
                modifier = innerModifier.weight(1f)
            )
        }
    }
}