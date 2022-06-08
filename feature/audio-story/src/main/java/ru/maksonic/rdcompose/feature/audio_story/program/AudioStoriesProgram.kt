package ru.maksonic.rdcompose.feature.audio_story.program

import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch
import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.domain.stories.FetchStoriesUseCase
import ru.maksonic.rdcompose.feature.audio_story.AudioStoryUi
import ru.maksonic.rdcompose.feature.audio_story.R
import ru.maksonic.rdcompose.feature.audio_story.model.Cmd
import ru.maksonic.rdcompose.feature.audio_story.model.Msg
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

/**
 * @Author maksonic on 07.06.2022
 */
class AudioStoriesProgram @Inject constructor(
    private val fetchStoriesUseCase: FetchStoriesUseCase,
    private val mapper: AudioStoriesDomainToUiMapper
) : ElmProgram<Msg, Cmd> {
    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchStories -> fetchStories(consumer)
            is Cmd.NextStories -> scrollNextStory(cmd)
            is Cmd.PreviousStories -> scrollPreviousStory(cmd)
        }
    }

    private fun fetchStories(consumer: (Msg) -> Unit) {
        val storiesUi = mapper.mapFromList(fetchStoriesUseCase())
        consumer(Msg.Internal.Success(storiesUi))
    }

    @OptIn(ExperimentalPagerApi::class)
    private fun scrollNextStory(cmd: Cmd.NextStories) {
        cmd.scope.launch {
            cmd.pagerState.scrollToPage(max(0, cmd.pagerState.currentPage - 1))
        }
    }
    @OptIn(ExperimentalPagerApi::class)
    private fun scrollPreviousStory(cmd: Cmd.PreviousStories) {
        cmd.scope.launch {
            cmd.pagerState.scrollToPage(min(cmd.pagerState.pageCount - 1, cmd.pagerState.currentPage + 1))
        }
    }
}