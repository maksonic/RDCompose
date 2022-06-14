package ru.maksonic.rdcompose.screen.podcast_list.model

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import kotlinx.coroutines.CoroutineScope
import ru.maksonic.rdcompose.core.elm.Command

/**
 * @Author maksonic on 29.05.2022
 */
sealed class Cmd : Command {
   object FetchData: Cmd()
    object RefreshData: Cmd()

    @OptIn(ExperimentalMaterialApi::class)
    data class PlayPodcast(
        val scope: CoroutineScope,
        val playerSheet: BottomSheetScaffoldState
    ) : Cmd()
}