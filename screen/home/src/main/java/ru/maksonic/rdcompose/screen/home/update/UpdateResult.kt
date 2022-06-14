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
    fun errorFetching(model: Model, msg: Msg.Internal.Error): Update
    fun onNextStory(model: Model, msg: Msg.Ui.OnNextStoryClicked): Update
    fun onPreviousStory(model: Model, msg: Msg.Ui.OnPreviousStoryClicked): Update
    fun viewCurrentStory(model: Model, msg: Msg.Internal.ViewedCurrentStory): Update
    fun fetchingAllContent(model: Model, msg: Msg.Ui.FetchAllData): Update
    fun fetchingSuccess(model: Model, msg: Msg.Internal.SuccessData): Update

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

        override fun errorFetching(model: Model, msg: Msg.Internal.Error): Update =
            model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isSuccess = false,
                    isRefreshing = false,
                    isError = true,
                    errorMsg = msg.errorMsg
                )
            ) to emptySet()

        @OptIn(ExperimentalPagerApi::class)
        override fun onNextStory(model: Model, msg: Msg.Ui.OnNextStoryClicked): Update =
            model to setOf(Cmd.NextStories(msg.scope, msg.pagerState))

        @OptIn(ExperimentalPagerApi::class)
        override fun onPreviousStory(model: Model, msg: Msg.Ui.OnPreviousStoryClicked): Update =
            model to setOf(Cmd.PreviousStories(msg.scope, msg.pagerState))

        override fun viewCurrentStory(model: Model, msg: Msg.Internal.ViewedCurrentStory): Update =
            model.copy(story = model.story.copy(isViewedStory = true)) to emptySet()

        override fun fetchingAllContent(model: Model, msg: Msg.Ui.FetchAllData): Update =
            model.copy(baseModel = model.baseModel.copy(isLoading = true)) to setOf(
                Cmd.FetchAllData
            )

        override fun fetchingSuccess(model: Model, msg: Msg.Internal.SuccessData): Update =
            model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isSuccess = true,
                    isRefreshing = false,
                    isError = false
                ),
                content = model.content.copy(
                    newPodcasts = msg.newPodcasts,
                    recommendPodcasts = msg.recommendPodcasts,
                    topPodcasts = msg.topPodcasts,
                    randomPodcasts = msg.randomPodcasts
                )
            ) to emptySet()
    }
}