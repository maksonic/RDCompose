package ru.maksonic.rdcompose.domain.podcasts.new_podcasts

/**
 * @Author maksonic on 10.06.2022
 */
/*class FetchNewPodcastsInteractor @Inject constructor(
    private val repository: PodcastRepository
) : BaseUseCase<Podcasts, Nothing> {
    override suspend fun invoke(args: Nothing?): Podcasts = repository.fetchNewPodcasts()*/
  /*  override suspend fun invoke(args: Nothing?): Podcasts =
        repository.fetchAllPodcasts().transform { tryFetchNewPodcasts ->
        tryFetchNewPodcasts.onSuccess { podcasts ->
            val newPodcasts = podcasts.filter { it.isNew }
            emit(Result.success(newPodcasts))
        }
        tryFetchNewPodcasts.onFailure { error -> emit(Result.failure(error)) }
    }*/
/* override suspend fun invoke(args: Nothing?): Podcasts =
        repository.fetchAllPodcasts().transform { tryFetchNewPodcasts ->
            tryFetchNewPodcasts.onSuccess { podcasts ->
                val newPodcasts = podcasts.filter { it.isNew }
                emit(Result.success(newPodcasts))
            }
            tryFetchNewPodcasts.onFailure { error -> emit(Result.failure(error)) }
        }*/