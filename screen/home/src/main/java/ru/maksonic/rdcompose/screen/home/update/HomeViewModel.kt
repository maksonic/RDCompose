package ru.maksonic.rdcompose.screen.home.update

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.rdcompose.core.elm.BaseModel
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
    initialCmd = setOf(Cmd.FetchStories, Cmd.FetchAllContent),
    initialModel = Model(BaseModel(isLoading = true)),
    subscriptions = listOf(audioStoriesProgram, fetchContentProgram),
    navigator = navigator
) {
    override fun update(msg: Msg, model: Model): Update =
        when (msg) {
            is Msg.Ui.ShowStory -> updateResult.showStory(model, msg)
            is Msg.Ui.CloseStory -> updateResult.closeStory(model, msg)
            is Msg.Internal.StoriesSuccess -> updateResult.storiesSuccess(model, msg)
            is Msg.Internal.Error -> updateResult.errorFetching(model, msg)
            is Msg.Ui.OnNextStoryClicked -> updateResult.onNextStory(model, msg)
            is Msg.Ui.OnPreviousStoryClicked -> updateResult.onPreviousStory(model, msg)
            is Msg.Internal.ViewedCurrentStory -> updateResult.viewCurrentStory(model, msg)
            is Msg.Ui.FetchAllData -> updateResult.fetching(model)
            is Msg.Ui.RefreshHomeContent -> updateResult.refreshing(model)
            is Msg.Internal.SuccessData -> updateResult.fetchingSuccess(model, msg)
        }
}