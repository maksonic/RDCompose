package ru.maksonic.rdcompose.shared.ui_widget.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.maksonic.rdcompose.shared.theme.RDTheme

/**
 * @Author maksonic on 23.05.2022
 */
@Composable
fun PrimaryButton(
    action: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    clickTimeOut: Long = 300
) {

    val scope = rememberCoroutineScope()
    val disabledElevation = RDTheme.elevation.elevationDisable
    var isEnabled by rememberSaveable {
        mutableStateOf(true)
    }

    Button(
        onClick = {
            scope.launch {
                isEnabled = false
                action.invoke()
                delay(clickTimeOut)
                isEnabled = true
            }
        },
        colors = ButtonDefaults.buttonColors(
            RDTheme.color.primary,
            contentColor = RDTheme.color.onPrimary
        ),
        shape = RDTheme.shape.cornerBig,
        elevation = ButtonDefaults.elevation(
            defaultElevation = disabledElevation,
            pressedElevation = disabledElevation,
            disabledElevation = disabledElevation
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(RDTheme.componentSize.btnPrimaryHeight)
            .padding(
                start = RDTheme.padding.dp16,
                end = RDTheme.padding.dp16
            )
    ) {
        Text(
            text = title,
            color = RDTheme.color.onPrimary,
            style = RDTheme.typography.title
        )
    }
}
