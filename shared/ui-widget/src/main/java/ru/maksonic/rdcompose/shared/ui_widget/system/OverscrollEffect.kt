package ru.maksonic.rdcompose.shared.ui_widget.system

import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.gestures.OverScrollConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

/**
 * @Author maksonic on 08.06.2022
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OverscrollEffect(content: @Composable () -> Unit) {
    val enabledOverscroll = compositionLocalOf<OverScrollConfiguration?> {
        OverScrollConfiguration()
    }
    val overScroll = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) null else enabledOverscroll

    CompositionLocalProvider(LocalOverScrollConfiguration provides overScroll?.current) {
        content()
    }
}