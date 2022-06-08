package ru.maksonic.rdcompose.feature.audio_story.model

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import ru.maksonic.rdcompose.core.elm.Command

/**
 * @Author maksonic on 07.06.2022
 */
sealed class Cmd: Command {
    object FetchStories : Cmd()
    @OptIn(ExperimentalPagerApi::class)
    data class NextStories(val scope: CoroutineScope, val pagerState: PagerState) : Cmd()
    @OptIn(ExperimentalPagerApi::class)
    data class PreviousStories(val scope: CoroutineScope, val pagerState: PagerState) : Cmd()
}