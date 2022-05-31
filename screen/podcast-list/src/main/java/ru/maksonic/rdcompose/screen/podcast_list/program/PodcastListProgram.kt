package ru.maksonic.rdcompose.screen.podcast_list.program

import ru.maksonic.rdcompose.core.store.KeyStore
import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.domain.podcasts.FetchPodcastsUseCase
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.podcast_list.model.Cmd
import ru.maksonic.rdcompose.screen.podcast_list.model.Msg
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastDomainToUiMapper
import javax.inject.Inject

/**
 * @Author maksonic on 30.05.2022
 */
class PodcastListProgram @Inject constructor(
    private val fetchPodcastsUseCase: FetchPodcastsUseCase,
    private val mapper: PodcastDomainToUiMapper,
    private val keyStore: KeyStore.NavigationKey,
    private val navigator: MainNavigator,
) : ElmProgram<Msg, Cmd> {

    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchPodcastList -> fetchPodcasts(consumer)
            is Cmd.InitToolbarTitle -> initTitle(consumer)
        }
    }

    private suspend fun fetchPodcasts(consumer: (Msg) -> Unit) {
        val categoryId = navigator.getArgument(keyStore.passedCategoryIdKey)

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

    private fun initTitle(consumer: (Msg) -> Unit) {
        val title = navigator.getArgument(keyStore.passedCategoryNameKey)
        consumer(Msg.Internal.FetchTopBarTitle(title))
    }
}