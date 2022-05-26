package ru.maksonic.rdcompose.screen.categories.program

import kotlinx.coroutines.delay
import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.domain.categories.FetchCategoriesUseCase
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.categories.model.Cmd
import ru.maksonic.rdcompose.screen.categories.model.Msg
import ru.maksonic.rdcompose.shared.ui_model.category.CategoryDomainToUiMapper
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
class CategoriesProgram @Inject constructor(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase,
    private val mapper: CategoryDomainToUiMapper,
    private val navigator: MainNavigator
): ElmProgram<Msg, Cmd> {

    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchCategories -> fetchCategories(consumer)
            is Cmd.RefreshCategories -> fetchCategories(consumer)
            is Cmd.NavigateToPodcastList -> navigator.categoriesToCategoryPodcasts(cmd.categoryId)
        }
    }

    private suspend fun fetchCategories(consumer: (Msg) -> Unit) {
        fetchCategoriesUseCase().collect { categoriesRequest ->
            categoriesRequest.onSuccess { categoriesList ->
                val categories = mapper.mapFromList(categoriesList)
                delay(3000)
                consumer(Msg.Internal.FetchingSuccess(categories))
            }
            categoriesRequest.onFailure { throwable ->
                consumer(Msg.Internal.Error(throwable.message))
            }
        }
    }
}