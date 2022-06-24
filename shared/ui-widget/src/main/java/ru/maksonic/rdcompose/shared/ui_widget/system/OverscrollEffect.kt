package ru.maksonic.rdcompose.shared.ui_widget.system

import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.OverscrollConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

/**
 * @Author maksonic on 08.06.2022
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OverscrollEffect(content: @Composable () -> Unit) {
    val enabledOverscroll = compositionLocalOf<OverscrollConfiguration?> {
        OverscrollConfiguration()
    }
    val overScroll = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) null else enabledOverscroll

    CompositionLocalProvider(LocalOverscrollConfiguration provides overScroll?.current) {
        content()
    }
}