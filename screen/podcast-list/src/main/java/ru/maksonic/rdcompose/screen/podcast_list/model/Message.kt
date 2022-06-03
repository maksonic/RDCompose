package ru.maksonic.rdcompose.screen.podcast_list.model

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Immutable
import kotlinx.coroutines.CoroutineScope
import ru.maksonic.rdcompose.core.elm.Message
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastUi

/**
 * @Author maksonic on 29.05.2022
 */
@Immutable
sealed class Msg : Message {
    sealed class Ui : Msg() {
        object RetryFetchPodcasts : Ui()

        @OptIn(ExperimentalMaterialApi::class)
        data class OnPodcastClicked(
            val scope: CoroutineScope,
            val playerSheet: BottomSheetScaffoldState
        ) : Ui()
    }

    sealed class Internal : Msg() {
        data class Success(val podcasts: List<PodcastUi>) : Internal()
        data class Error(val errorMsg: String? = "") : Internal()
        data class FetchTopBarTitle(val title: String) : Internal()
        data class FetchedCategoryInfo(val categoryInfo: CategoryInfo) : Internal()
    }
}