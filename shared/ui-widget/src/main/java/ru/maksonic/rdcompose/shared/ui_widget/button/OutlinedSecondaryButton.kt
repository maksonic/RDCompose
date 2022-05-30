package ru.maksonic.rdcompose.shared.ui_widget.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 30.05.2022
 */
@Composable
fun OutlinedSecondaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    borderColor: Color = RDTheme.color.primary,
    onButtonColor: Color = RDTheme.color.primary,
    backgroundColor: Color = Color.Transparent,
    rippleColor: Color = RDTheme.color.primary,
    shape: Shape = RDTheme.shape.cornerNormal,
    title: String = "",
    iconId: Int? = null
) {

    Box(
        modifier
            .defaultMinSize(minHeight = 36.dp, minWidth = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        val innerModifier = Modifier

        Row(
            innerModifier
                .fillMaxSize()
                .border(width = 1.dp, color = borderColor, shape = shape)
                .clip(shape)
                .background(backgroundColor)
                .rippleClickable(rippleColor = rippleColor) { onClick() },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (iconId == null) {
                Text(
                    text = title,
                    style = RDTheme.typography.title,
                    textAlign = TextAlign.Center,
                    color = onButtonColor,
                    modifier = innerModifier.padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )
                )
            } else {
                Icon(
                    painter = painterResource(iconId),
                    contentDescription = "",
                    tint = onButtonColor,
                    modifier = innerModifier.padding()
                )
                Text(
                    text = title,
                    style = RDTheme.typography.title,
                    textAlign = TextAlign.Center,
                    color = onButtonColor,
                    modifier = innerModifier.padding(
                        top = 8.dp,
                        bottom = 8.dp
                    )
                )
            }
        }
    }
}