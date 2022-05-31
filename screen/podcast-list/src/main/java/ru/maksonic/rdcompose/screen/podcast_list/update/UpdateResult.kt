package ru.maksonic.rdcompose.screen.podcast_list.update

import ru.maksonic.rdcompose.screen.podcast_list.model.Cmd
import ru.maksonic.rdcompose.screen.podcast_list.model.Model
import ru.maksonic.rdcompose.screen.podcast_list.model.Msg
import javax.inject.Inject

/**
 * @Author maksonic on 30.05.2022
 */
interface UpdateResult {
    fun retryFetching(model: Model): Update
    fun onPodcastClicked(model: Model, msg: Msg.Ui.OnPodcastClicked): Update
    fun success(model: Model, msg: Msg.Internal.Success): Update
    fun error(model: Model, msg: Msg.Internal.Error): Update
    fun fetchCategoryInfo(model: Model, msg: Msg.Internal.FetchedCategoryInfo): Update

    class Base @Inject constructor() : UpdateResult {

        override fun retryFetching(model: Model): Update =
            model.copy(isLoading = true, isError = false) to setOf(Cmd.FetchPodcastList)

        override fun onPodcastClicked(model: Model, msg: Msg.Ui.OnPodcastClicked): Update =
            model to emptySet()

        override fun success(model: Model, msg: Msg.Internal.Success): Update =
            model.copy(
                isLoading = false,
                isSuccess = true,
                isError = false,
                podcasts = msg.podcasts
            ) to emptySet()

        override fun error(model: Model, msg: Msg.Internal.Error): Update =
            model.copy(
                isLoading = false,
                isSuccess = false,
                isError = true,
                errorMsg = msg.errorMsg.toString()
            ) to emptySet()

        override fun fetchCategoryInfo(
            model: Model, msg: Msg.Internal.FetchedCategoryInfo
        ): Update =
            model.copy(categoryInfo = msg.categoryInfo) to emptySet()
    }
}
