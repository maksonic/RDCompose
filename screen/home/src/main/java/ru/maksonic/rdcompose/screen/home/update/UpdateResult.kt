package ru.maksonic.rdcompose.screen.home.update

import com.google.accompanist.pager.ExperimentalPagerApi
import ru.maksonic.rdcompose.screen.home.model.Cmd
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.model.Msg
import javax.inject.Inject

/**
 * @Author maksonic on 06.06.2022
 */
interface UpdateResult {
    fun retryFetchingStories(model: Model, msg: Msg.Ui.RetryFetchingStories): Update
    fun showStory(model: Model, msg: Msg.Ui.ShowStory): Update
    fun closeStory(model: Model, msg: Msg.Ui.CloseStory): Update
    fun storiesSuccess(model: Model, msg: Msg.Internal.StoriesSuccess): Update
    fun storiesError(model: Model, msg: Msg.Internal.Error): Update
    fun onNextStory(model: Model, msg: Msg.Ui.OnNextStoryClicked): Update
    fun onPreviousStory(model: Model, msg: Msg.Ui.OnPreviousStoryClicked): Update

    class Base @Inject constructor() : UpdateResult {
        override fun retryFetchingStories(model: Model, msg: Msg.Ui.RetryFetchingStories): Update =
            model.copy(story = model.story.copy(isLoading = true)) to setOf(Cmd.FetchStories)

        override fun showStory(model: Model, msg: Msg.Ui.ShowStory): Update =
            model.copy(
                story = model.story.copy(currentStory = msg.storyIndex, isShowedStoryDialog = true)
            ) to emptySet()

        override fun closeStory(model: Model, msg: Msg.Ui.CloseStory): Update =
            model.copy(story = model.story.copy(isShowedStoryDialog = false)) to emptySet()

        override fun storiesSuccess(model: Model, msg: Msg.Internal.StoriesSuccess): Update =
            model.copy(
                story = model.story.copy(
                    isLoading = false,
                    isFetched = true,
                    isError = false,
                    stories = msg.stories
                )
            ) to emptySet()

        override fun storiesError(model: Model, msg: Msg.Internal.Error): Update =
            model.copy(
                story = model.story.copy(
                    isLoading = false,
                    isFetched = false,
                    isError = true,
                    stories = emptyList()
                )
            ) to emptySet()

        @OptIn(ExperimentalPagerApi::class)
        override fun onNextStory(model: Model, msg: Msg.Ui.OnNextStoryClicked): Update =
            model to setOf(Cmd.NextStories(msg.scope, msg.pagerState))

        @OptIn(ExperimentalPagerApi::class)
        override fun onPreviousStory(model: Model, msg: Msg.Ui.OnPreviousStoryClicked): Update =
            model to setOf(Cmd.PreviousStories(msg.scope, msg.pagerState))


    }
}