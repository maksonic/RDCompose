package ru.maksonic.rdcompose.screen.categories.program

import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.domain.categories.Categories
import ru.maksonic.rdcompose.domain.categories.interactor.FetchCategoriesInteractor
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.categories.model.Cmd
import ru.maksonic.rdcompose.screen.categories.model.Msg
import ru.maksonic.rdcompose.shared.ui_model.category.category.CategoryDomainToUiMapper
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
class CategoriesProgram @Inject constructor(
    private val interactor: FetchCategoriesInteractor,
    private val mapper: CategoryDomainToUiMapper,
    private val navigator: MainNavigator,
) : ElmProgram<Msg, Cmd> {

    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchCategories -> executeInteractor(interactor.fetchData(), consumer)
            is Cmd.RefreshCategories -> executeInteractor(interactor.refreshData(), consumer)
            is Cmd.NavigateToPodcastList -> navigator.categoriesToCategoryPodcasts(cmd.categoryId)
        }
    }

    private suspend fun executeInteractor(data: Categories, consumer: (Msg) -> Unit) {
        data.collect { categoriesRequest ->
            categoriesRequest.onSuccess { categoriesList ->
                val categories = mapper.mapFromList(categoriesList).sortedBy { it.id }
                consumer(Msg.Internal.Success(categories))
            }
            categoriesRequest.onFailure { throwable ->
                consumer(Msg.Internal.Error(throwable.message.toString()))
            }
        }
    }
}