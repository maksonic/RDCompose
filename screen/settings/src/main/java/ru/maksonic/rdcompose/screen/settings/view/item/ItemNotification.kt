package ru.maksonic.rdcompose.screen.settings.view.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.maksonic.rdcompose.screen.settings.R.*
import ru.maksonic.rdcompose.screen.settings.widget.ItemWithClick
import ru.maksonic.rdcompose.screen.settings.widget.ItemWithSwitch
import ru.maksonic.rdcompose.screen.settings.widget.SettingTitle
import ru.maksonic.rdcompose.shared.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
internal fun ItemNotification(
    videosCheckedState: MutableState<Boolean>,
    newsCheckedState: MutableState<Boolean>,
    modifier: Modifier = Modifier) {
    val dp16 = RDTheme.padding.dp16

    SettingTitle(title = stringResource(string.title_notification))
    Card(
        backgroundColor = RDTheme.color.surface,
        shape = RDTheme.shape.cornerNormal,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16)
    ) {
        Column {
            ItemWithSwitch(
                title = stringResource(string.item_push_videos),
                icon = R.drawable.ic_settings_item_push_videos,
                checkedState = videosCheckedState,
                action = {}
            )
            ItemWithSwitch(
                title = stringResource(string.item_push_news),
                icon = R.drawable.ic_settings_item_push_news,
                checkedState = newsCheckedState,
                action = {}
            )
        }
    }
}