package ru.maksonic.rdcompose.screen.podcast_list.program

import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import kotlinx.coroutines.launch
import ru.maksonic.rdcompose.core.store.KeyStore
import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.domain.categories.FetchCategoryByIdUseCase
import ru.maksonic.rdcompose.domain.podcasts.FetchPodcastsUseCase
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.podcast_list.model.CategoryInfo
import ru.maksonic.rdcompose.screen.podcast_list.model.Cmd
import ru.maksonic.rdcompose.screen.podcast_list.model.Msg
import ru.maksonic.rdcompose.shared.ui_model.category.category.CategoryDomainToUiMapper
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastDomainToUiMapper
import javax.inject.Inject

/**
 * @Author maksonic on 30.05.2022
 */
class PodcastListProgram @Inject constructor(
    private val fetchPodcastsUseCase: FetchPodcastsUseCase,
    private val fetchCategoryByIdUseCase: FetchCategoryByIdUseCase,
    private val categoryMapper: CategoryDomainToUiMapper,
    private val mapper: PodcastDomainToUiMapper,
    private val keyStore: KeyStore.NavigationKey,
    private val navigator: MainNavigator,
) : ElmProgram<Msg, Cmd> {

    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchPodcastList -> fetchPodcasts(consumer)
            is Cmd.InitToolbarTitle -> initTitle(consumer)
            is Cmd.FetchCategoryInfo -> fetchCategoryInfo(consumer)
            is Cmd.PlayPodcast -> playPodcast(cmd)
        }
    }

    private fun initTitle(consumer: (Msg) -> Unit) {
        val title = navigator.getStringArgument(keyStore.passedCategoryNameKey)
        consumer(Msg.Internal.FetchTopBarTitle(title))
    }

    private suspend fun fetchCategoryInfo(consumer: (Msg) -> Unit) {
        val categoryId = navigator.getStringArgument(keyStore.passedCategoryIdKey)
        fetchCategoryByIdUseCase(categoryId).collect { tryFindBiYd ->
            tryFindBiYd.onSuccess { categoryDomain ->
                val category = categoryMapper.mapFrom(categoryDomain)
                consumer(
                    Msg.Internal.FetchedCategoryInfo(
                        categoryInfo = CategoryInfo(
                            name = category.name,
                            categoryImage = categoryDomain.image
                        )
                    )
                )
            }
            tryFindBiYd.onFailure { throwable ->
                consumer(Msg.Internal.Error(throwable.message))
            }
        }
    }

    private suspend fun fetchPodcasts(consumer: (Msg) -> Unit) {
        val categoryId = navigator.getStringArgument(keyStore.passedCategoryIdKey)

        fetchPodcastsUseCase(categoryId).collect { categoriesRequest ->
            categoriesRequest.onSuccess { categoriesList ->
                val podcasts = mapper.mapFromList(categoriesList).sortedBy { it.id }
                consumer(Msg.Internal.Success(podcasts))
            }
            categoriesRequest.onFailure { throwable ->
                consumer(Msg.Internal.Error(throwable.message))
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    private suspend fun playPodcast(cmd: Cmd.PlayPodcast) {
        cmd.scope.launch { cmd.playerSheet.bottomSheetState.animateTo(BottomSheetValue.Expanded) }
    }
}