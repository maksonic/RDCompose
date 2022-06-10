package ru.maksonic.rdcompose.screen.home.program

import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.domain.podcasts.new_podcasts.FetchNewPodcastsInteractor
import ru.maksonic.rdcompose.screen.home.model.Cmd
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastDomainToUiMapper
import javax.inject.Inject

/**
 * @Author maksonic on 10.06.2022
 */
class FetchContentProgram @Inject constructor(
    private val fetchNewPodcastsInteractor: FetchNewPodcastsInteractor,
    private val mapper: PodcastDomainToUiMapper
) : ElmProgram<Msg, Cmd> {
    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNewPodcasts -> fetchNewPodcasts(consumer)
            else -> {}
        }
    }

    private suspend fun fetchNewPodcasts(consumer: (Msg) -> Unit) {
        fetchNewPodcastsInteractor().collect { newPodcastsRequest ->
            newPodcastsRequest.onSuccess { podcastsDomain ->
                val podcastsUi = mapper.mapFromList(podcastsDomain)
                consumer(Msg.Internal.NewPodcastsSuccess(podcastsUi))
            }
            newPodcastsRequest.onFailure { throwable ->
                consumer(Msg.Internal.Error(throwable.message.toString()))
            }
        }
    }
}