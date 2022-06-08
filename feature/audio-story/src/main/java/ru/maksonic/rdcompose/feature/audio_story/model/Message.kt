package ru.maksonic.rdcompose.feature.audio_story.model

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import ru.maksonic.rdcompose.core.elm.Message
import ru.maksonic.rdcompose.feature.audio_story.AudioStoryUi

/**
 * @Author maksonic on 07.06.2022
 */
sealed class Msg : Message {
    sealed class Ui : Msg() {
        object CollapseStory : Ui()

        @OptIn(ExperimentalPagerApi::class)
        data class OnNextStoryClicked(
            val scope: CoroutineScope,
            val pagerState: PagerState
        ) : Ui()

        @OptIn(ExperimentalPagerApi::class)
        data class OnPreviousStoryClicked(
            val scope: CoroutineScope,
            val pagerState: PagerState
        ) : Ui()

        data class AddStoryToFavorites(val storyId: Long) : Ui()
        data class RemoveStoryFromFavorites(val storyId: Long) : Ui()
    }

    sealed class Internal : Msg() {
        object FetchStories : Internal()
        data class SetViewedStory(val storyId: Long) : Internal()
        data class Success(val stories: List<AudioStoryUi>) : Internal()
        data class Error(val errorMsg: String) : Internal()
    }
}