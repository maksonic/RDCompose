package ru.maksonic.rdcompose.screen.collections

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ru.maksonic.rdcompose.shared.theme.RDTheme

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
fun CollectionsScreen() {
    Scaffold(backgroundColor = RDTheme.color.background) {
        Text("Collections")
    }
}