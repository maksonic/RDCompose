package ru.maksonic.rdcompose.screen.home.program

import android.util.Log
import kotlinx.coroutines.delay
import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.domain.podcasts.new_podcasts.FetchRecommendPodcasts
import ru.maksonic.rdcompose.screen.home.model.Cmd
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastDomainToUiMapper
import javax.inject.Inject

/**
 * @Author maksonic on 10.06.2022
 */
class FetchContentProgram @Inject constructor(
    private val fetchRecommendPodcasts: FetchRecommendPodcasts,
    private val mapper: PodcastDomainToUiMapper
) : ElmProgram<Msg, Cmd> {
    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            //  is Cmd.FetchNewPodcasts -> fetchNewPodcasts(consumer)
            is Cmd.FetchAllData -> fetchRecommendPodcasts(consumer)
            else -> {}
        }
    }

    private suspend fun fetchAllData(consumer: (Msg) -> Unit) {
        delay(3000)
        consumer(
            Msg.Internal.SuccessData(
                newPodcasts = emptyList(),
                recommendPodcasts = emptyList(),
                topPodcasts = emptyList(),
                randomPodcasts = emptyList()
            )
        )
    }

    private suspend fun fetchRecommendPodcasts(consumer: (Msg) -> Unit) {
        fetchRecommendPodcasts().collect { useCaseResponse ->
            useCaseResponse.onSuccess { podcastsDomain ->
                val podcasts = mapper.mapFromList(podcastsDomain)
                Log.e("UI", "${podcasts.count()}")
                consumer(Msg.Internal.SuccessData(
                    newPodcasts = emptyList(),
                    recommendPodcasts = podcasts,
                    topPodcasts = emptyList(),
                    randomPodcasts = emptyList()
                ))
            }
            useCaseResponse.onFailure { error ->
                consumer(Msg.Internal.Error(error.message.toString()))
            }
        }
    }
/*private suspend fun fetchAllData(consumer: (Msg) -> Unit) {
        delay(3000)
        fetchHomeScreenContentUseCase().collect { tryFetchHomeContent ->
            tryFetchHomeContent.onSuccess { domainContent ->
                val isNew = mapper.mapFromList(domainContent.newPodcasts)
                val isRecommend = mapper.mapFromList(domainContent.recommendPodcasts)
                val isTop = mapper.mapFromList(domainContent.topPodcasts)
                val isRandom = mapper.mapFromList(domainContent.randomPodcasts)
                consumer(
                    Msg.Internal.SuccessData(
                        newPodcasts = isNew,
                        recommendPodcasts = isRecommend,
                        topPodcasts = isTop,
                        randomPodcasts = isRandom
                    )
                )
            }
            tryFetchHomeContent.onFailure { error -> consumer(Msg.Internal.Error(error.message)) }

        }
    }*/

    /*private suspend fun fetchNewPodcasts(consumer: (Msg) -> Unit) {
        fetchNewPodcastsInteractor().collect { newPodcastsRequest ->
            newPodcastsRequest.onSuccess { podcastsDomain ->
                val podcastsUi = mapper.mapFromList(podcastsDomain)
                Log.e("UI", "$podcastsUi")
                consumer(Msg.Internal.NewPodcastsSuccess(podcastsUi))
            }
            newPodcastsRequest.onFailure { throwable ->
                consumer(Msg.Internal.Error(throwable.message.toString()))
            }
        }
    }*/
}