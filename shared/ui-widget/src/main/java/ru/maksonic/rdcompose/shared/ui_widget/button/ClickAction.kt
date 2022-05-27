package ru.maksonic.rdcompose.shared.ui_widget.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 24.05.2022
 */
fun Modifier.clickAction(timeOut: Long = 300, onClick: () -> Unit) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "debouncedClick"
        properties["timeOut"] = timeOut
        properties["onClick"] = onClick
    }
) {
    var isEnabled by rememberSaveable {
        mutableStateOf(true)
    }
    val coroutineScope = rememberCoroutineScope()
    val currentClickListener by rememberUpdatedState(onClick)

    Modifier.rippleClickable(isEnabled) {
        coroutineScope.launch {
            isEnabled = false
            currentClickListener.invoke()
            delay(timeOut)
            isEnabled = true
        }
    }
}

fun Modifier.rippleClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = onClick,
        role = role,
        indication = rememberRipple(color = RDTheme.color.primary, bounded = true),
        interactionSource = remember { MutableInteractionSource() }
    )
}
