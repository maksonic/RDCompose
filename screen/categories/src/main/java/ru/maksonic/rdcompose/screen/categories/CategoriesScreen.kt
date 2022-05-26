package ru.maksonic.rdcompose.screen.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.maksonic.rdcompose.navigation.api.R
import ru.maksonic.rdcompose.shared.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.ScreenTitleDisplay

/**
 * @Author maksonic on 25.05.2022
 */

@Composable
fun CategoriesScreen() {
    CategoriesScreenUi()
}
@Composable
fun CategoriesScreenUi(modifier: Modifier = Modifier) {
    Scaffold(
        backgroundColor = RDTheme.color.background
    ) { padding ->
        LazyColumn(modifier.padding(padding)) {
           item { ScreenTitleDisplay(title = stringResource(id = R.string.scr_categories)) }
        }
    }
}