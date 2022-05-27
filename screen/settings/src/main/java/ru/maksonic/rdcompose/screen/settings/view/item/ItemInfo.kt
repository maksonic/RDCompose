package ru.maksonic.rdcompose.screen.settings.view.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.maksonic.rdcompose.screen.settings.R.string
import ru.maksonic.rdcompose.screen.settings.widget.ItemWithClick
import ru.maksonic.rdcompose.screen.settings.widget.SettingTitle
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
internal fun ItemInfo(modifier: Modifier = Modifier) {
    val dp16 = RDTheme.padding.dp16

    SettingTitle(title = stringResource(string.title_info))
    Card(
        backgroundColor = RDTheme.color.surface,
        shape = RDTheme.shape.cornerNormal,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16)
    ) {
        Column {
            ItemWithClick(
                title = stringResource(string.item_write_message),
                icon = R.drawable.ic_mail_outlined,
                action = {}
            )
            ItemWithClick(
                title = stringResource(string.item_privacy),
                icon = R.drawable.ic_privacy,
                action = {}
            )
            ItemWithClick(
                title = stringResource(string.item_terms_of_use),
                icon = R.drawable.ic_terms_of_use,
                action = {}
            )
        }
    }
}