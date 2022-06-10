package ru.maksonic.rdcompose.domain.podcasts.new_podcasts

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.domain.base.BaseUseCase
import ru.maksonic.rdcompose.domain.base.Podcasts
import ru.maksonic.rdcompose.domain.categories.FetchCategoriesUseCase
import ru.maksonic.rdcompose.domain.podcasts.FetchPodcastsUseCase
import javax.inject.Inject

/**
 * @Author maksonic on 10.06.2022
 */
class FetchNewPodcastsInteractor @Inject constructor(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase,
    private val fetchPodcastsUseCase: FetchPodcastsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : BaseUseCase<Podcasts, Nothing> {
    override suspend fun invoke(args: Nothing?): Podcasts = withContext(dispatcher) {
        flow {
            fetchCategoriesUseCase().collect { tryFetchCategory ->
                tryFetchCategory.onSuccess { categories ->
                    for (category in categories) {
                        fetchPodcastsUseCase(category.categoryId).collect { tryFetchPodcasts ->
                            tryFetchPodcasts.onSuccess { podcasts ->
                                emit(Result.success(podcasts))
                            }
                            tryFetchPodcasts.onFailure { emit(Result.failure(it)) }
                        }
                    }
                }
                tryFetchCategory.onFailure { emit(Result.failure(it)) }
            }
        }
    }
}