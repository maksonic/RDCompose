package ru.maksonic.rdcompose.domain.stories

/**
 * @Author maksonic on 08.06.2022
 */
interface StoriesRepo {
    fun fetchStories(): List<AudioStoryDomain>
}