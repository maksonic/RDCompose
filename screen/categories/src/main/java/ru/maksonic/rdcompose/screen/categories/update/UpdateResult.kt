package ru.maksonic.rdcompose.screen.categories.update

import ru.maksonic.rdcompose.screen.categories.model.Cmd
import ru.maksonic.rdcompose.screen.categories.model.Model
import ru.maksonic.rdcompose.screen.categories.model.Msg
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
interface UpdateResult {
    fun fetching(model: Model): Update
    fun refreshing(model: Model): Update
    fun onCategoryClicked(model: Model, msg: Msg.Ui.OnCategoryClick): Update
    fun success(model: Model, msg: Msg.Internal.Success): Update
    fun error(model: Model, msg: Msg.Internal.Error): Update

    class Base @Inject constructor() : UpdateResult {
        override fun fetching(model: Model): Update =
            model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = true,
                    isSuccess = false,
                    isRefreshing = false,
                    isError = false
                ),

                ) to setOf(Cmd.FetchCategories)

        override fun refreshing(model: Model): Update =
            model.copy(
                baseModel = model.baseModel.copy(isRefreshing = true),
            ) to setOf(Cmd.RefreshCategories)

        override fun onCategoryClicked(model: Model, msg: Msg.Ui.OnCategoryClick): Update =
            model to setOf(Cmd.NavigateToPodcastList(msg.categoryId, msg.categoryName))

        override fun success(model: Model, msg: Msg.Internal.Success): Update =
            model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isSuccess = true,
                    isRefreshing = false,
                    isError = false
                ),
                categories = msg.categories
            ) to emptySet()

        override fun error(model: Model, msg: Msg.Internal.Error): Update =
            model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isSuccess = false,
                    isRefreshing = false,
                    isError = true,
                    errorMsg = msg.errorMessage
                ),
                categories = emptyList()
            ) to emptySet()
    }
}