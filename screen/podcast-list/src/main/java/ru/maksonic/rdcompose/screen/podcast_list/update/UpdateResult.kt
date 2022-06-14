package ru.maksonic.rdcompose.screen.podcast_list.update

import androidx.compose.material.ExperimentalMaterialApi
import ru.maksonic.rdcompose.screen.podcast_list.model.Cmd
import ru.maksonic.rdcompose.screen.podcast_list.model.Model
import ru.maksonic.rdcompose.screen.podcast_list.model.Msg
import javax.inject.Inject

/**
 * @Author maksonic on 30.05.2022
 */
interface UpdateResult {
    fun swipeRefresh(model: Model): Update
    fun retryFetching(model: Model): Update
    fun onPodcastClicked(model: Model, msg: Msg.Ui.OnPodcastClicked): Update
    fun success(model: Model, msg: Msg.Internal.Success): Update
    fun refreshed(model: Model, msg: Msg.Internal.RefreshedPodcasts): Update
    fun error(model: Model, msg: Msg.Internal.Error): Update

    class Base @Inject constructor() : UpdateResult {

        override fun swipeRefresh(model: Model): Update =
            model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isRefreshing = true,
                    isError = false
                )
            ) to setOf(Cmd.RefreshData)

        override fun retryFetching(model: Model): Update =
            model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = true,
                    isError = false
                )
            ) to setOf(Cmd.FetchData)

        @OptIn(ExperimentalMaterialApi::class)
        override fun onPodcastClicked(model: Model, msg: Msg.Ui.OnPodcastClicked): Update =
            model to setOf(Cmd.PlayPodcast(msg.scope, msg.playerSheet))

        override fun success(model: Model, msg: Msg.Internal.Success): Update =
            model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isSuccess = true,
                    isRefreshing = false,
                    isError = false
                ),
                podcasts = msg.podcasts,
                categoryInfo = msg.categoryInfo
            ) to emptySet()

        override fun refreshed(model: Model, msg: Msg.Internal.RefreshedPodcasts): Update =
            model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isRefreshing = false,
                    isError = false,
                    isSuccess = true
                ),
                podcasts = msg.podcasts
            ) to emptySet()

        override fun error(model: Model, msg: Msg.Internal.Error): Update =
            model.copy(
                baseModel = model.baseModel.copy(
                    isLoading = false,
                    isSuccess = false,
                    isRefreshing = false,
                    isError = true,
                    errorMsg = msg.errorMsg.toString()
                )
            ) to emptySet()
    }
}
