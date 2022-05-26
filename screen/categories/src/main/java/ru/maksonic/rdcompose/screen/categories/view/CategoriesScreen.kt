package ru.maksonic.rdcompose.screen.categories.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.rdcompose.navigation.api.R
import ru.maksonic.rdcompose.screen.categories.model.Msg
import ru.maksonic.rdcompose.screen.categories.update.CategoriesViewModel
import ru.maksonic.rdcompose.shared.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_model.category.CategoryUi
import ru.maksonic.rdcompose.shared.ui_widget.ScreenTitleDisplay

/**
 * @Author maksonic on 25.05.2022
 */
typealias Message = (Msg) -> Unit

@Composable
fun CategoriesScreen() {
    val viewModel: CategoriesViewModel = hiltViewModel()
    CategoriesScreenUi(viewModel)
}

@Composable
fun CategoriesScreenUi(viewModel: CategoriesViewModel, modifier: Modifier = Modifier) {
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg

    Scaffold(
        backgroundColor = RDTheme.color.background
    ) { padding ->
        LazyColumn(modifier.padding(padding)) {
            item { ScreenTitleDisplay(title = stringResource(id = R.string.scr_categories)) }

            items(items = model.value.categories.sortedBy { it.id }) { category ->
                ItemCardCategory(
                    category = category,
                    onClick = { sendMsg(Msg.Ui.OnCategoryClick(category.id)) }
                )
            }
        }
    }
}