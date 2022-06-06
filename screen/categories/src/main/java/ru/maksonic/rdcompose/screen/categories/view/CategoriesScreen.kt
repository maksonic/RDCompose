package ru.maksonic.rdcompose.screen.categories.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.maksonic.rdcompose.core.utils.PlayerBackPressed
import ru.maksonic.rdcompose.navigation.api.R
import ru.maksonic.rdcompose.screen.categories.model.Msg
import ru.maksonic.rdcompose.screen.categories.update.CategoriesViewModel
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.ErrorViewState
import ru.maksonic.rdcompose.shared.ui_widget.LoadingViewState
import ru.maksonic.rdcompose.shared.ui_widget.ScreenTitleDisplay

/**
 * @Author maksonic on 25.05.2022
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesScreen(playerBottomSheetState: BottomSheetScaffoldState) {
    val viewModel: CategoriesViewModel = hiltViewModel()
    CategoriesScreenUi(viewModel, playerBottomSheetState)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesScreenUi(
    viewModel: CategoriesViewModel,
    playerBottomSheetState: BottomSheetScaffoldState,
    modifier: Modifier = Modifier
) {
    PlayerBackPressed(playerBottomSheetState)

    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg

    Scaffold(
        backgroundColor = RDTheme.color.background,
        modifier = modifier.padding(top = RDTheme.componentSize.smallTopBarHeight)
    ) { padding ->

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = model.value.baseModel.isRefreshing),
            onRefresh = { sendMsg(Msg.Ui.RefreshCategories) },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    scale = true,
                    contentColor = RDTheme.color.primary,
                    backgroundColor = RDTheme.color.surface,
                )
            }
        ) {
            LazyColumn(
                modifier
                    .fillMaxHeight()
                    .padding(padding)
            ) {
                item { ScreenTitleDisplay(title = stringResource(id = R.string.scr_categories)) }

                when {
                    model.value.baseModel.isLoading -> item {
                        Box(modifier.fillParentMaxHeight(1f)) {
                            LoadingViewState()
                        }
                    }
                    model.value.baseModel.isSuccess -> {
                        items(items = model.value.categories) { category ->
                            ItemCardCategory(
                                category = category,
                                onClick = {
                                    sendMsg(
                                        Msg.Ui.OnCategoryClick(categoryId = category.categoryId)
                                    )
                                }
                            )
                        }
                    }
                    model.value.baseModel.isError -> {
                        item {
                            Box(modifier.fillParentMaxHeight(1f)) {
                                ErrorViewState(
                                    errorMessage = model.value.baseModel.errorMsg.toString(),
                                    retryAction = { sendMsg(Msg.Ui.FetchCategories) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}