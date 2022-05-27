package ru.maksonic.rdcompose.shared.ui_widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
    val errorEmojiList = listOf(
        "\uD83D\uDE13", "\uD83D\uDE1E", "\uD83D\uDE23", "\uD83E\uDDD0",
        "\uD83D\uDE31", "\uD83D\uDE22", "\uD83D\uDE28", "\uD83D\uDE16", "\uD83D\uDE2D",
        "\uD83D\uDE22", "\uD83D\uDE25", "\uD83D\uDE30",
    )
    val errorEmoji = rememberSaveable() {
        mutableStateOf(errorEmojiList.first())
    }

    LaunchedEffect(Unit) {
        errorEmoji.value = errorEmojiList.random()
    }
    Box(
        modifier
            .fillMaxSize()
            .background(RDTheme.color.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {


            Text(
                text = errorEmoji.value,
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
