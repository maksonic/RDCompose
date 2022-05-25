package ru.maksonic.rdcompose.shared.ui_widget.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
fun Switch(
    modifier: Modifier = Modifier,
    isChecked: MutableState<Boolean> = mutableStateOf(false),
    checkedColor: Color = Color.Black,
    uncheckedColor: Color = Color.Gray,
    thumbColor: Color = Color.White
) {
    Row(modifier.wrapContentWidth()) {
        val switchColor by animateColorAsState(
            targetValue = if (isChecked.value) checkedColor
            else uncheckedColor
        )
        val offset by animateDpAsState(targetValue = if (isChecked.value) 14.dp else 0.dp)

        Box(
            modifier
                .wrapContentSize(Alignment.Center)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) {
                    isChecked.value = isChecked.value != true
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier
                    .height(14.dp)
                    .width(34.dp)
                    .clip(CircleShape)
                    .background(switchColor)

            )
            Box(
                modifier
                    .size(20.dp)
                    .offset(x = offset)
                    .background(thumbColor, shape = CircleShape)
                    .border(BorderStroke(width = 2.dp, switchColor), shape = CircleShape)
            )
        }
    }
}