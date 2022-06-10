package ru.maksonic.rdcompose.screen.categories.update

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.rdcompose.core.elm.BaseModel
import ru.maksonic.rdcompose.core.elm.ElmRuntime
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.categories.model.Cmd
import ru.maksonic.rdcompose.screen.categories.model.Model
import ru.maksonic.rdcompose.screen.categories.model.Msg
import ru.maksonic.rdcompose.screen.categories.program.CategoriesProgram
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
internal typealias Update = Pair<Model, Set<Cmd>>

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val updateResult: UpdateResult,
    categoriesProgram: CategoriesProgram,
    navigator: MainNavigator
) : ElmRuntime<Model, Msg, Cmd>(
    initialModel = Model(baseModel = BaseModel(isLoading = true)),
    initialCmd = setOf(Cmd.FetchCategories),
    subscriptions = listOf(categoriesProgram),
    navigator = navigator
) {

    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Ui.FetchCategories -> updateResult.fetching(model)
        is Msg.Ui.RefreshCategories -> updateResult.refreshing(model)
        is Msg.Ui.OnCategoryClick -> updateResult.onCategoryClicked(model, msg)
        is Msg.Internal.Success -> updateResult.success(model, msg)
        is Msg.Internal.Error -> updateResult.error(model, msg)
    }
}