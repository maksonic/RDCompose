package ru.maksonic.rdcompose.data.stories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.R
import ru.maksonic.rdcompose.domain.stories.AudioStoryDomain
import ru.maksonic.rdcompose.domain.stories.Stories
import ru.maksonic.rdcompose.domain.stories.StoriesRepo
import javax.inject.Inject

/**
 * @Author maksonic on 08.06.2022
 */
// TODO: Переписать код на получение данных из Firebase.
class StoriesRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : StoriesRepo {
    override fun fetchStories(): Stories = flow {
        val data = listOf(
            AudioStoryDomain(0, R.drawable.item_story_sample_0),
            AudioStoryDomain(1, R.drawable.item_story_sample_1),
            AudioStoryDomain(2, R.drawable.item_story_sample_2),
            AudioStoryDomain(3, R.drawable.item_story_sample_3),
            AudioStoryDomain(4, R.drawable.item_story_sample_4),
        )
        emit(Result.success(data.plus(data)))
    }.flowOn(dispatcher)
}