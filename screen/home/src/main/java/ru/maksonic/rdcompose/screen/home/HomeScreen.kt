package ru.maksonic.rdcompose.screen.home

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ru.maksonic.rdcompose.shared.theme.RDTheme

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
fun HomeScreen() {
    Scaffold(backgroundColor = RDTheme.color.background) {
        Text("HOME")

    }

}