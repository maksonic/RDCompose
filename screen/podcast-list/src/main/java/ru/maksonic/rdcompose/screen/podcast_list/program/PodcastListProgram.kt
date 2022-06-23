package ru.maksonic.rdcompose.screen.podcast_list.program

import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import kotlinx.coroutines.launch
import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.core.store.KeyStore
import ru.maksonic.rdcompose.domain.categories.usecase.FetchCategoryByIdUseCase
import ru.maksonic.rdcompose.domain.podcasts.Podcasts
import ru.maksonic.rdcompose.domain.podcasts.interactor.FetchPodcastsByCategoryInteractor
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
    private val interactor: FetchPodcastsByCategoryInteractor,
    private val fetchCategory: FetchCategoryByIdUseCase,
    private val categoryMapper: CategoryDomainToUiMapper,
    private val mapper: PodcastDomainToUiMapper,
    private val keyStore: KeyStore.NavigationKey,
    private val navigator: MainNavigator,
) : ElmProgram<Msg, Cmd> {

    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchData -> {
                executeInteractor({ categoryId -> interactor.fetchData(categoryId) }, consumer)
            }
            is Cmd.RefreshData -> {
                executeInteractor({ categoryId -> interactor.refreshData(categoryId) }, consumer)
            }
            is Cmd.PlayPodcast -> playPodcast(cmd)
        }
    }

    private suspend fun executeInteractor(
        useCase: suspend (Long) -> Podcasts, consumer: (Msg) -> Unit
    ) {
        val passedCategoryId = navigator.getLongArgument(keyStore.passedCategoryIdKey)
        fetchCategory(passedCategoryId).collect { categoryResponse ->
            categoryResponse.onSuccess { categoryDomain ->
                val category = categoryMapper.mapFrom(categoryDomain)
                useCase(category.id).collect { podcastsResponse ->
                    podcastsResponse.onSuccess { podcastsDomain ->
                        val podcasts = mapper.mapFromList(podcastsDomain)
                        consumer(
                            Msg.Internal.Success(
                                podcasts = podcasts,
                                CategoryInfo(category.name, category.image)
                            )
                        )
                    }
                    podcastsResponse.onFailure { error ->
                        consumer(Msg.Internal.Error(error.message))
                    }
                }
            }
            categoryResponse.onFailure { error ->
                consumer(Msg.Internal.Error(error.message))
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    private suspend fun playPodcast(cmd: Cmd.PlayPodcast) {
        cmd.scope.launch { cmd.playerSheet.bottomSheetState.animateTo(BottomSheetValue.Expanded) }
    }
}
