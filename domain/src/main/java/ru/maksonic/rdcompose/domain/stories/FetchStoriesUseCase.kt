package ru.maksonic.rdcompose.domain.stories

import ru.maksonic.rdcompose.domain.base.BaseUseCase
import javax.inject.Inject

/**
 * @Author maksonic on 08.06.2022
 */
class FetchStoriesUseCase @Inject constructor(
    private val repo: StoriesRepo
    ): BaseUseCase<Stories, Nothing> {
    override suspend fun invoke(args: Nothing?): Stories = repo.fetchStories()
}