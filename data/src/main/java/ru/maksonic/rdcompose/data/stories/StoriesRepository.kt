package ru.maksonic.rdcompose.data.stories

import ru.maksonic.rdcompose.data.R
import ru.maksonic.rdcompose.domain.stories.AudioStoryDomain
import ru.maksonic.rdcompose.domain.stories.StoriesRepo

/**
 * @Author maksonic on 08.06.2022
 */
class StoriesRepository : StoriesRepo {
    override fun fetchStories(): List<AudioStoryDomain> = listOf(
        AudioStoryDomain(0, R.drawable.item_story_sample_0),
        AudioStoryDomain(1, R.drawable.item_story_sample_1),
        AudioStoryDomain(2, R.drawable.item_story_sample_2),
        AudioStoryDomain(3, R.drawable.item_story_sample_3),
        AudioStoryDomain(4, R.drawable.item_story_sample_4),
    )
}