package ru.maksonic.rdcompose.domain.stories

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 08.06.2022
 */
typealias Stories = Flow<Result<List<AudioStoryDomain>>>
interface StoriesRepo {
    fun fetchStories(): Stories
}