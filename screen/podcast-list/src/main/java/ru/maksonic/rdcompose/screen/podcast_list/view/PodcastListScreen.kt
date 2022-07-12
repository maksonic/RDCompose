package ru.maksonic.rdcompose.screen.podcast_list.view

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import ru.maksonic.rdcompose.core.utils.PlayerBackPressed
import ru.maksonic.rdcompose.screen.podcast_list.model.Model
import ru.maksonic.rdcompose.screen.podcast_list.model.Msg
import ru.maksonic.rdcompose.screen.podcast_list.update.PodcastListViewModel
import ru.maksonic.rdcompose.screen.podcast_list.view.widget.SuccessPodcastsViewState
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.topbar.TopAppBarNormal
import ru.maksonic.rdcompose.shared.ui_widget.viewstate.ErrorViewState
import ru.maksonic.rdcompose.shared.ui_widget.viewstate.LoadingViewState

/**
 * @Author maksonic on 26.05.2022
 */
internal typealias Message = (Msg) -> Unit

fun extractMediaSourceFromUri(context: Context, uri: String): MediaSource {
    return ProgressiveMediaSource.Factory(DefaultDataSource.Factory(context))
        .createMediaSource(MediaItem.fromUri(Uri.parse(uri)))
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PodcastListScreen(playerBottomSheetState: BottomSheetScaffoldState) {
    val viewModel: PodcastListViewModel = hiltViewModel()
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg
    PodcastListScreenUi(viewModel, model.value, sendMsg, playerBottomSheetState)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PodcastListScreenUi(
    viewModel: PodcastListViewModel,
    model: Model,
    sendMsg: Message,
    playerBottomSheetState: BottomSheetScaffoldState,
    modifier: Modifier = Modifier
) {
    PlayerBackPressed(playerBottomSheetState)

    val ctx = LocalContext.current
    val mediaSource = remember { mutableStateOf(extractMediaSourceFromUri(ctx, "")) }
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val titleVisibility = remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } }
    val alphaBg = remember {
        derivedStateOf { if (lazyListState.firstVisibleItemIndex > 0) 1f else 0.7f }
    }
    val trackSelector = DefaultTrackSelector(ctx)
    val exoPlayer = ExoPlayer.Builder(ctx).setTrackSelector(trackSelector).build()
    exoPlayer.apply {
        // AudioAttributes here from exoplayer package !!!
        val attr = AudioAttributes.Builder().setUsage(C.USAGE_MEDIA)
            .setContentType(C.CONTENT_TYPE_MUSIC)
            .build()
        // In 2.9.X you don't need to manually handle audio focus :D
        setAudioAttributes(attr, true)
        setMediaSource(mediaSource.value)
        // THAT IS ALL YOU NEED
        playWhenReady = true
    }

    Scaffold(
        backgroundColor = RDTheme.color.background,
        modifier = modifier.systemBarsPadding(),
    ) { padding ->

        Box(
            modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(bottom = RDTheme.componentSize.playerCollapsedHeight)
        ) {
            when {
                model.baseModel.isLoading -> {
                    LoadingViewState(
                        modifier.padding(top = RDTheme.componentSize.smallTopBarHeight)
                    )
                }
                model.baseModel.isError -> {
                    ErrorViewState(
                        modifier.padding(top = RDTheme.componentSize.smallTopBarHeight),
                        retryAction = { sendMsg(Msg.Ui.RetryFetchPodcasts) },
                        errorMessage = model.baseModel.errorMsg
                    )
                }
                model.baseModel.isSuccess -> {
                    SuccessPodcastsViewState(
                        model = model,
                        sendMsg = sendMsg,
                        scope = scope,
                        playerSheet = playerBottomSheetState,
                        lazyListState = lazyListState,
                    )
                }
            }

            TopAppBarNormal(
                title = model.categoryInfo.name,
                titleVisibilityState = titleVisibility.value,
                bgAlpha = alphaBg.value,
                backPressed = { viewModel.backPressed() }
            )
        }
    }
}

