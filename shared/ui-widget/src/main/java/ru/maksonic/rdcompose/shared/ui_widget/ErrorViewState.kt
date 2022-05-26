package ru.maksonic.rdcompose.shared.ui_widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.maksonic.rdcompose.shared.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R.string

/**
 * @Author maksonic on 26.05.2022
 */
@Composable
fun ErrorViewState(
    errorMessage: String,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .background(RDTheme.color.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "\uD83D\uDE22",
                fontSize = 56.sp,
                color = RDTheme.color.secondaryText
            )
            Spacer(modifier.height(RDTheme.padding.dp32))

            Text(
                text = errorMessage,
                style = RDTheme.typography.caption,
                color = RDTheme.color.secondaryText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier.height(RDTheme.padding.dp32))

            Button(
                onClick = retryAction,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = RDTheme.color.primary,
                    contentColor = RDTheme.color.onPrimary
                )
            ) {
                Text(
                    stringResource(id = string.hint_retry_loading),
                    style = RDTheme.typography.body
                )
            }
        }
    }
}
