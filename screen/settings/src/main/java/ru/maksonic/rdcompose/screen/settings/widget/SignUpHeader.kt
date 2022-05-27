package ru.maksonic.rdcompose.screen.settings.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.PrimaryButton

/**
 * @Author maksonic on 27.05.2022
 */
@Composable
fun SignUpHeader(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .background(RDTheme.color.surface)
            .padding(bottom = RDTheme.padding.dp16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.title_sign_up_widget),
            style = RDTheme.typography.display,
            color = RDTheme.color.primaryText,
            modifier = modifier.padding(RDTheme.padding.dp8)
        )
        Text(
            text = stringResource(id = R.string.body_sign_up_widget),
            style = RDTheme.typography.title,
            color = RDTheme.color.primaryText,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(RDTheme.padding.dp16)
        )
        PrimaryButton(action = {}, title = stringResource(id = R.string.btn_title_create_account))
    }
}