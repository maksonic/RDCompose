package ru.maksonic.rdcompose.screen.settings.view.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.maksonic.rdcompose.screen.settings.R.string
import ru.maksonic.rdcompose.screen.settings.model.Model
import ru.maksonic.rdcompose.screen.settings.model.Msg
import ru.maksonic.rdcompose.screen.settings.view.Message
import ru.maksonic.rdcompose.screen.settings.widget.ItemWithClick
import ru.maksonic.rdcompose.screen.settings.widget.SettingTitle
import ru.maksonic.rdcompose.screen.settings.widget.SwitchThemeRadioButtons
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
internal fun ItemAppTheme(model: Model, sendMsg: Message, modifier: Modifier = Modifier) {
    val dp16 = RDTheme.padding.dp16
    val rotatePointer = animateFloatAsState(
        targetValue = if (model.isExpandedThemeSelector) 90f else 0f
    )
    val backgroundItemColor =
        if (model.isExpandedThemeSelector) RDTheme.color.divider else RDTheme.color.surface


    SettingTitle(stringResource(string.title_theme))
    Card(
        backgroundColor = RDTheme.color.surface,
        shape = RDTheme.shape.cornerNormal,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16)
    ) {
        Column {
            ItemWithClick(
                title = stringResource(string.item_change_theme),
                backgroundColor = backgroundItemColor,
                icon = R.drawable.ic_settings_item_theme,
                action = { sendMsg(Msg.Ui.ShowThemeSelector) },
                rotateIcon = rotatePointer.value
            )
            AnimatedVisibility(visible = model.isExpandedThemeSelector) {
                SwitchThemeRadioButtons(model, sendMsg)
            }
        }
    }
}