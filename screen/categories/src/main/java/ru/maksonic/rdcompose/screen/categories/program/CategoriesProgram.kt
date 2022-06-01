package ru.maksonic.rdcompose.screen.categories.program

import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.domain.categories.CategoryDomain
import ru.maksonic.rdcompose.domain.categories.FetchCategoriesUseCase
import ru.maksonic.rdcompose.domain.categories.RefreshCategoriesUseCase
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.categories.model.Cmd
import ru.maksonic.rdcompose.screen.categories.model.Msg
import ru.maksonic.rdcompose.shared.ui_model.category.category.CategoryDomainToUiMapper
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
class CategoriesProgram @Inject constructor(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase,
    private val refreshCategoriesUseCase: RefreshCategoriesUseCase,
    private val mapper: CategoryDomainToUiMapper,
    private val navigator: MainNavigator
) : ElmProgram<Msg, Cmd> {

    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchCategories -> execution(fetchCategoriesUseCase(), consumer)
            is Cmd.RefreshCategories -> execution(refreshCategoriesUseCase(), consumer)
            is Cmd.NavigateToPodcastList -> navigateToPodcasts(cmd)
        }
    }

    private suspend fun execution(
        useCase: Flow<Result<List<CategoryDomain>>>, consumer: (Msg) -> Unit
    ) {
        useCase.collect { categoriesRequest ->
            categoriesRequest.onSuccess { categoriesList ->
                val categories = mapper.mapFromList(categoriesList).sortedBy { it.id }
                consumer(Msg.Internal.Success(categories))
            }
            categoriesRequest.onFailure { throwable ->
                consumer(Msg.Internal.Error(throwable.message))
            }
        }
    }

    private fun navigateToPodcasts(cmd: Cmd.NavigateToPodcastList) {
        navigator.categoriesToCategoryPodcasts(cmd.categoryId)
    }
}