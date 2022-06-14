package ru.maksonic.rdcompose.screen.categories.view.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.maksonic.rdcompose.navigation.api.R
import ru.maksonic.rdcompose.screen.categories.model.Model
import ru.maksonic.rdcompose.screen.categories.model.Msg
import ru.maksonic.rdcompose.screen.categories.view.Message
import ru.maksonic.rdcompose.shared.ui_widget.component.ScreenTitleDisplay
import ru.maksonic.rdcompose.shared.ui_widget.system.OverscrollEffect
import ru.maksonic.rdcompose.shared.ui_widget.system.SwipeRefreshDefault

/**
 * @Author maksonic on 14.06.2022
 */
@Composable
internal fun SuccessCategoriesViewState(
    model: Model,
    sendMsg: Message,
    modifier: Modifier = Modifier
) {
    SwipeRefreshDefault(
        isRefreshing = model.baseModel.isRefreshing,
        onRefresh = { sendMsg(Msg.Ui.RefreshCategories) }) {

        OverscrollEffect {
            LazyColumn(
                modifier
                    .fillMaxSize()
            ) {
                item { ScreenTitleDisplay(title = stringResource(id = R.string.scr_categories)) }

                items(items = model.categories) { category ->
                    ItemCardCategory(
                        category = category,
                        onClick = {
                            sendMsg(Msg.Ui.OnCategoryClick(categoryId = category.id ?: 0))
                        }
                    )
                }
            }
        }
    }
}