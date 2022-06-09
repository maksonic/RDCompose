package ru.maksonic.rdcompose.screen.home.model

import androidx.compose.runtime.Immutable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import ru.maksonic.rdcompose.core.elm.Message
import ru.maksonic.rdcompose.shared.ui_model.category.stories.AudioStoryUi

/**
 * @Author maksonic on 06.06.2022
 */
@Immutable
sealed class Msg : Message {

    sealed class Ui : Msg() {
        object Initial : Ui()
        data class ShowStory(val storyIndex: Int) : Ui()
        object CloseStory : Ui()

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
    }

    sealed class Internal : Msg() {
        data class StoriesSuccess(val stories: List<AudioStoryUi>) : Internal()
        data class StoriesError(val errorMsg: String) : Internal()
        object SetCurrentStory : Internal()
    }
}