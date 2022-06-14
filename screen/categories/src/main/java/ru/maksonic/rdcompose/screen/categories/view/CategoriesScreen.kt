package ru.maksonic.rdcompose.screen.categories.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.rdcompose.core.utils.PlayerBackPressed
import ru.maksonic.rdcompose.screen.categories.model.Model
import ru.maksonic.rdcompose.screen.categories.model.Msg
import ru.maksonic.rdcompose.screen.categories.update.CategoriesViewModel
import ru.maksonic.rdcompose.screen.categories.view.widget.SuccessCategoriesViewState
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.viewstate.ErrorViewState
import ru.maksonic.rdcompose.shared.ui_widget.viewstate.LoadingViewState

/**
 * @Author maksonic on 25.05.2022
 */
internal typealias Message = (Msg) -> Unit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesScreen(playerBottomSheetState: BottomSheetScaffoldState) {
    val viewModel: CategoriesViewModel = hiltViewModel()
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg
    CategoriesScreenUi(model.value, sendMsg, playerBottomSheetState)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CategoriesScreenUi(
    model: Model,
    sendMsg: Message,
    playerBottomSheetState: BottomSheetScaffoldState,
    modifier: Modifier = Modifier
) {
    PlayerBackPressed(playerBottomSheetState)

    Scaffold(
        backgroundColor = RDTheme.color.background,
        modifier = modifier
            .systemBarsPadding()
            .padding(
                top = RDTheme.componentSize.smallTopBarHeight,
                bottom = RDTheme.componentSize.playerCollapsedHeight
            )
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
                                    errorMessage = model.value.baseModel.errorMsg,
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

