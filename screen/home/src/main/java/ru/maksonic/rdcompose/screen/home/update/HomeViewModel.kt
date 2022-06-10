package ru.maksonic.rdcompose.screen.home.update

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.rdcompose.core.elm.ElmRuntime
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.home.model.Cmd
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.screen.home.program.AudioStoriesProgram
import ru.maksonic.rdcompose.screen.home.program.FetchContentProgram
import javax.inject.Inject

/**
 * @Author maksonic on 06.06.2022
 */
internal typealias Update = Pair<Model, Set<Cmd>>

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val updateResult: UpdateResult,
    audioStoriesProgram: AudioStoriesProgram,
    fetchContentProgram: FetchContentProgram,
    navigator: MainNavigator
) : ElmRuntime<Model, Msg, Cmd>(
    initialCmd = setOf(Cmd.FetchStories, Cmd.FetchNewPodcasts),
    initialModel = Model(),
    subscriptions = listOf(audioStoriesProgram, fetchContentProgram),
    navigator = navigator
) {
    override fun update(msg: Msg, model: Model): Update =
        when (msg) {
            is Msg.Ui.RetryFetchingStories -> updateResult.retryFetchingStories(model, msg)
            is Msg.Ui.ShowStory -> updateResult.showStory(model, msg)
            is Msg.Ui.CloseStory -> updateResult.closeStory(model, msg)
            is Msg.Internal.StoriesSuccess -> updateResult.storiesSuccess(model, msg)
            is Msg.Internal.Error -> updateResult.storiesError(model, msg)
            is Msg.Ui.OnNextStoryClicked -> updateResult.onNextStory(model, msg)
            is Msg.Ui.OnPreviousStoryClicked -> updateResult.onPreviousStory(model, msg)
            is Msg.Internal.ViewedCurrentStory -> model.copy(story = model.story.copy(isViewedStory = true)) to emptySet()
            is Msg.Internal.NewPodcastsSuccess -> model.copy(newPodcasts = msg.podcasts) to emptySet()
        }
}