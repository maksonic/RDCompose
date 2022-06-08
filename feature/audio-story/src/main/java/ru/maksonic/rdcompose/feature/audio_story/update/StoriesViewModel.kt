package ru.maksonic.rdcompose.feature.audio_story.update

import androidx.compose.runtime.mutableStateOf
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.rdcompose.core.elm.BaseModel
import ru.maksonic.rdcompose.core.elm.ElmRuntime
import ru.maksonic.rdcompose.feature.audio_story.model.Cmd
import ru.maksonic.rdcompose.feature.audio_story.model.Model
import ru.maksonic.rdcompose.feature.audio_story.model.Msg
import ru.maksonic.rdcompose.feature.audio_story.program.AudioStoriesProgram
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

/**
 * @Author maksonic on 07.06.2022
 */
internal typealias Update = Pair<Model, Set<Cmd>>

@OptIn(ExperimentalPagerApi::class)
@HiltViewModel
class StoriesViewModel @Inject constructor(
    audioStoriesProgram: AudioStoriesProgram,
) : ElmRuntime<Model, Msg, Cmd>(
    initialModel = Model(baseModel = BaseModel(isLoading = true)),
    initialCmd = setOf(Cmd.FetchStories),
    subscriptions = listOf(audioStoriesProgram)
) {
    override fun update(msg: Msg, model: Model): Update =
        when (msg) {
            is Msg.Ui.AddStoryToFavorites -> model to emptySet()
            is Msg.Ui.RemoveStoryFromFavorites -> model to emptySet()
            is Msg.Internal.FetchStories -> model.copy(baseModel = model.baseModel.copy(isLoading = true)) to setOf(
                Cmd.FetchStories
            )
            is Msg.Internal.SetViewedStory -> model to emptySet()
            is Msg.Internal.Success -> model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isSuccess = true,
                    isError = false
                ), stories = msg.stories
            ) to emptySet()
            is Msg.Internal.Error -> model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isError = true,
                    errorMsg = msg.errorMsg
                )
            ) to emptySet()
            is Msg.Ui.CollapseStory -> model to emptySet()
            is Msg.Ui.OnNextStoryClicked -> {
                model to setOf(Cmd.NextStories(msg.scope, msg.pagerState))
            }
            is Msg.Ui.OnPreviousStoryClicked -> {
                model to setOf(Cmd.PreviousStories(msg.scope, msg.pagerState))

            }
        }
}