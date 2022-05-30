package ru.maksonic.rdcompose.screen.settings.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.rdcompose.screen.settings.model.Model
import ru.maksonic.rdcompose.screen.settings.model.Msg
import ru.maksonic.rdcompose.screen.settings.view.Message
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.button.rippleClickable

/**
 * @Author maksonic on 27.05.2022
 */
@Composable
internal fun SwitchThemeRadioButtons(model: Model, sendMsg: Message) {
    Column(Modifier.padding(8.dp)) {
        model.themeVariants.forEach { selectedTheme ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .rippleClickable(rippleColor = RDTheme.color.primary) {
                        sendMsg(
                            Msg.Ui.SwitchAppTheme(
                                selectedTheme
                            )
                        )
                    }
            ) {
                RadioButton(
                    selected = model.currentAppTheme.value == selectedTheme,
                    onClick = { sendMsg(Msg.Ui.SwitchAppTheme(selectedTheme)) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = RDTheme.color.primary,
                        unselectedColor = RDTheme.color.secondary
                    )
                )
                Text(
                    text = selectedTheme.title,
                    style = RDTheme.typography.body,
                    color = RDTheme.color.primaryText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = RDTheme.padding.dp8)
                )
            }
        }
    }
}
