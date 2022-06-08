package ru.maksonic.rdcompose.feature.audio_story.program

import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.domain.stories.AudioStoryDomain
import ru.maksonic.rdcompose.feature.audio_story.AudioStoryUi
import javax.inject.Inject

/**
 * @Author maksonic on 08.06.2022
 */
class AudioStoriesDomainToUiMapper @Inject constructor() : Mapper<AudioStoryDomain, AudioStoryUi> {
    override fun mapTo(o: AudioStoryUi) = AudioStoryDomain(
        storyId = o.storyId,
        storyBackground = o.storyBackground,
        storySoundFile = o.storySoundFile,
    )

    override fun mapFrom(i: AudioStoryDomain) = AudioStoryUi(
        storyId = i.storyId,
        storyBackground = i.storyBackground,
        storySoundFile = i.storySoundFile,
    )
}