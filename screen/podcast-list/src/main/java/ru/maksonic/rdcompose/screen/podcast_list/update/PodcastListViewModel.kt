package ru.maksonic.rdcompose.screen.podcast_list.update

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.rdcompose.core.elm.BaseModel
import ru.maksonic.rdcompose.core.elm.ElmRuntime
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.podcast_list.model.Cmd
import ru.maksonic.rdcompose.screen.podcast_list.model.Model
import ru.maksonic.rdcompose.screen.podcast_list.model.Msg
import ru.maksonic.rdcompose.screen.podcast_list.program.PodcastListProgram
import javax.inject.Inject

/**
 * @Author maksonic on 28.05.2022
 */
typealias Update = Pair<Model, Set<Cmd>>

@HiltViewModel
class PodcastListViewModel @Inject constructor(
    private val updateResult: UpdateResult,
    podcastListProgram: PodcastListProgram,
    navigator: MainNavigator
) : ElmRuntime<Model, Msg, Cmd>(
    initialModel = Model(baseModel = BaseModel(isLoading = true)),
    initialCmd = setOf(Cmd.FetchPodcastList, Cmd.InitToolbarTitle, Cmd.FetchCategoryInfo),
    subscriptions = listOf(podcastListProgram),
    navigator = navigator
) {
    override fun update(msg: Msg, model: Model): Update =
        when (msg) {
            is Msg.Internal.FetchTopBarTitle -> model.copy(titleTopBar = msg.title) to emptySet()
            is Msg.Ui.RetryFetchPodcasts -> updateResult.retryFetching(model)
            is Msg.Ui.OnPodcastClicked -> updateResult.onPodcastClicked(model, msg)
            is Msg.Internal.Success -> updateResult.success(model, msg)
            is Msg.Internal.Error -> updateResult.error(model, msg)
            is Msg.Internal.FetchedCategoryInfo -> updateResult.fetchCategoryInfo(model, msg)
        }
}
