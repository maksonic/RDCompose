package ru.maksonic.rdcompose.screen.home.update

import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.rdcompose.core.elm.ElmRuntime
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.home.model.Cmd
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.screen.home.program.AudioStoriesProgram
import javax.inject.Inject

/**
 * @Author maksonic on 06.06.2022
 */
internal typealias Update = Pair<Model, Set<Cmd>>

@HiltViewModel
class HomeViewModel @Inject constructor(
    audioStoriesProgram: AudioStoriesProgram,
    navigator: MainNavigator
) : ElmRuntime<Model, Msg, Cmd>(
    initialCmd = setOf(Cmd.FetchStories),
    initialModel = Model(),
    subscriptions = listOf(audioStoriesProgram),
    navigator = navigator
) {
    @OptIn(ExperimentalPagerApi::class)
    override fun update(msg: Msg, model: Model): Update =
        when (msg) {
            is Msg.Ui.Initial -> model to emptySet()
            is Msg.Ui.ShowStory -> {
                model.copy(
                    story = model.story.copy(
                        currentStory = msg.storyIndex,
                        isShowedStoryDialog = true
                    )
                ) to emptySet()
            }
            is Msg.Ui.CloseStory -> {
                model.copy(story = model.story.copy(isShowedStoryDialog = false)) to emptySet()
            }
            is Msg.Internal.StoriesSuccess -> model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isSuccess = true,
                    isError = false
                ),
                story = model.story.copy(stories = msg.stories)
            ) to emptySet()
            is Msg.Internal.StoriesError -> model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isSuccess = false,
                    isError = true,
                    errorMsg = msg.errorMsg
                ),
                story = model.story.copy(stories = emptyList())
            ) to emptySet()

            is Msg.Ui.OnNextStoryClicked -> {
                model to setOf(Cmd.NextStories(msg.scope, msg.pagerState))
            }
            is Msg.Ui.OnPreviousStoryClicked -> {
                model to setOf(Cmd.PreviousStories(msg.scope, msg.pagerState))

            }
            is Msg.Internal.SetCurrentStory -> model.copy(
                story = model.story.copy(
                    currentStory = model.story.currentStory + 1
                )
            ) to emptySet()
        }
}