package ru.maksonic.rdcompose.screen.home.update

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.rdcompose.core.elm.BaseModel
import ru.maksonic.rdcompose.core.elm.ElmRuntime
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.home.model.Cmd
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.screen.home.program.FetchStoriesProgram
import javax.inject.Inject

/**
 * @Author maksonic on 06.06.2022
 */
internal typealias Update = Pair<Model, Set<Cmd>>

@HiltViewModel
class HomeViewModel @Inject constructor(
    fetchStoriesProgram: FetchStoriesProgram,
    navigator: MainNavigator
) : ElmRuntime<Model, Msg, Cmd>(
    initialCmd = setOf(Cmd.FetchStories),
    initialModel = Model(baseModel = BaseModel()),
    subscriptions = listOf(fetchStoriesProgram),
    navigator = navigator
) {
    override fun update(msg: Msg, model: Model): Update =
        when (msg) {
            is Msg.Ui.Initial -> model to emptySet()
            is Msg.Ui.ShowStory -> model.copy(isShowedStoryDialog = true) to emptySet()
            is Msg.Ui.CloseStory -> model.copy(isShowedStoryDialog = false) to emptySet()
            is Msg.Internal.StoriesSuccess -> model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isSuccess = true,
                    isError = false
                ),
                stories = msg.stories
            ) to emptySet()
            is Msg.Internal.StoriesError -> model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isSuccess = false,
                    isError = true,
                    errorMsg = msg.errorMsg
                ),
                stories = emptyList()
            ) to emptySet()
        }
}