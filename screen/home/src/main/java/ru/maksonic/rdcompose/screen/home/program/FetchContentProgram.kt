package ru.maksonic.rdcompose.screen.home.program

import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.domain.podcasts.HomeContent
import ru.maksonic.rdcompose.domain.podcasts.interactor.FetchHomeContentInteractor
import ru.maksonic.rdcompose.screen.home.model.Cmd
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastDomainToUiMapper
import javax.inject.Inject

/**
 * @Author maksonic on 10.06.2022
 */
class FetchContentProgram @Inject constructor(
    private val interactor: FetchHomeContentInteractor,
    private val mapper: PodcastDomainToUiMapper
) : ElmProgram<Msg, Cmd> {
    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchAllContent -> executeInteractor(interactor.fetchData(), consumer)
            is Cmd.RefreshContent -> executeInteractor(interactor.refreshData(), consumer)
            else -> {}
        }
    }

    private suspend fun executeInteractor(data: HomeContent, consumer: (Msg) -> Unit) {
        data.collect { contentResponse ->
            contentResponse.onSuccess { domainContent ->
                val newPodcasts = mapper.mapFromList(domainContent.newPodcasts)
                val recommendPodcasts = mapper.mapFromList(domainContent.recommendPodcasts)
                val topPodcasts = mapper.mapFromList(domainContent.topPodcasts)
                val randomPodcasts = mapper.mapFromList(domainContent.randomPodcasts)
                consumer(
                    Msg.Internal.SuccessData(
                        newPodcasts, recommendPodcasts, topPodcasts, randomPodcasts
                    )
                )
            }
            contentResponse.onFailure { error ->
                consumer(Msg.Internal.Error(error.localizedMessage?.toString()))
            }
        }
    }
}
