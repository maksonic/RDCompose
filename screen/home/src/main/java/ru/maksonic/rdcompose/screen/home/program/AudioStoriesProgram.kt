package ru.maksonic.rdcompose.screen.home.program

import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.domain.stories.FetchStoriesUseCase
import ru.maksonic.rdcompose.screen.home.model.Cmd
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.shared.ui_model.category.stories.AudioStoriesDomainToUiMapper
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

/**
 * @Author maksonic on 07.06.2022
 */
class AudioStoriesProgram @Inject constructor(
    private val fetchStoriesUseCase: FetchStoriesUseCase,
    private val mapper: AudioStoriesDomainToUiMapper
) : ElmProgram<Msg, Cmd> {
    @OptIn(ExperimentalPagerApi::class)
    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchStories -> fetchStories(consumer)
            is Cmd.NextStories -> pageScrollChange(cmd.scope) {
                cmd.pagerState.animateScrollToPage(
                    min(
                        cmd.pagerState.pageCount.minus(1),
                        cmd.pagerState.currentPage.plus(1)
                    )
                )
            }
            is Cmd.PreviousStories -> pageScrollChange(cmd.scope) {
                cmd.pagerState.animateScrollToPage(
                    max(0, cmd.pagerState.currentPage.minus(1))
                )
            }
        }
    }

    private suspend fun fetchStories(consumer: (Msg) -> Unit) {
        fetchStoriesUseCase().collect { storiesRequest ->
            storiesRequest.onSuccess { storiesDomain ->
                val storiesUi = mapper.mapFromList(storiesDomain)
                consumer(Msg.Internal.StoriesSuccess(storiesUi))
            }
            storiesRequest.onFailure { throwable ->
                consumer(Msg.Internal.StoriesError(throwable.message.toString()))
            }
        }
    }

    /**
     * With no [delay], fast page changes cause spurious changes.
     * For example, when we quickly press "next", the page can scroll back.
     * */
    private fun pageScrollChange(scope: CoroutineScope, runChange: suspend () -> Unit) {
        scope.launch {
            delay(100)
            runChange()
        }
    }
}