package ru.maksonic.rdcompose.screen.home.program

import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.domain.stories.FetchStoriesUseCase
import ru.maksonic.rdcompose.feature.audio_story.program.AudioStoriesDomainToUiMapper
import ru.maksonic.rdcompose.screen.home.model.Cmd
import ru.maksonic.rdcompose.screen.home.model.Msg
import javax.inject.Inject

/**
 * @Author maksonic on 08.06.2022
 */
class FetchStoriesProgram @Inject constructor(
    private val fetchStoriesUseCase: FetchStoriesUseCase,
    private val mapper: AudioStoriesDomainToUiMapper
) : ElmProgram<Msg, Cmd> {
    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchStories -> fetchStories(consumer)
        }
    }

    private fun fetchStories(consumer: (Msg) -> Unit) {
        val storiesUi = mapper.mapFromList( fetchStoriesUseCase())
        if (storiesUi.isEmpty()) {
            consumer(Msg.Internal.StoriesError("Stories not found!"))
        } else {
            consumer(Msg.Internal.StoriesSuccess(storiesUi))
        }
    }
}