package ru.maksonic.rdcompose.screen.podcast_list.program

import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.core.store.KeyStore
import ru.maksonic.rdcompose.domain.categories.FetchCategoryByIdUseCase
import ru.maksonic.rdcompose.domain.podcasts.usecase.FetchPodcastsByCategoryUseCase
import ru.maksonic.rdcompose.domain.podcasts.PodcastDomain
import ru.maksonic.rdcompose.domain.podcasts.usecase.RefreshPodcastsByCategoryUseCase
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
    private val fetchCategoryByIdUseCase: FetchCategoryByIdUseCase,
    private val fetchPodcasts: FetchPodcastsByCategoryUseCase,
    private val refreshPodcasts: RefreshPodcastsByCategoryUseCase,
    private val categoryMapper: CategoryDomainToUiMapper,
    private val mapper: PodcastDomainToUiMapper,
    private val keyStore: KeyStore.NavigationKey,
    private val navigator: MainNavigator,
) : ElmProgram<Msg, Cmd> {

    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchData ->
                executeUseCase({ categoryId -> fetchPodcasts(categoryId) }, consumer)
            is Cmd.RefreshData ->
                executeUseCase({ categoryId -> refreshPodcasts(categoryId) }, consumer)
            is Cmd.PlayPodcast -> playPodcast(cmd)
        }
    }

    private suspend fun executeUseCase(
        useCase: suspend (categoryId: String) -> Flow<Result<List<PodcastDomain>>>,
        consumer: (Msg) -> Unit
    ) {
        val passedCategoryId = navigator.getLongArgument(keyStore.passedCategoryIdKey)
        delay(2000)
        fetchCategoryByIdUseCase(passedCategoryId).collect { findCategory ->
            findCategory.onSuccess { categoryDomain ->
                val category = categoryMapper.mapFrom(categoryDomain)
                useCase(category.categoryId).collect { podcastsResponse ->
                    podcastsResponse.onSuccess { podcastsDomain ->
                        val podcasts = mapper.mapFromList(podcastsDomain)
                        consumer(
                            Msg.Internal.Success(
                                podcasts, CategoryInfo(category.name, category.image)
                            )
                        )
                    }
                    podcastsResponse.onFailure { error ->
                        consumer(Msg.Internal.Error(error.message))
                    }
                }
            }
            findCategory.onFailure { error -> consumer(Msg.Internal.Error(error.message)) }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    private suspend fun playPodcast(cmd: Cmd.PlayPodcast) {
        cmd.scope.launch { cmd.playerSheet.bottomSheetState.animateTo(BottomSheetValue.Expanded) }
    }
}
