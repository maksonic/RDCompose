package ru.maksonic.rdcompose.screen.home.view.widget.new_podcast

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastUi
import ru.maksonic.rdcompose.shared.ui_widget.component.CoilShimmerImage
import ru.maksonic.rdcompose.shared.ui_widget.component.CoilSimpleImage
import ru.maksonic.rdcompose.shared.ui_widget.component.HorizontalPagerLightScroll

/**
 * @Author maksonic on 10.06.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun NewPodcastsViewPager(newPodcasts: List<PodcastUi>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(initialPage = 0)

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPagerLightScroll(
            pagesCount = newPodcasts.size,
            pagerState = pagerState,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) { page ->
            NewPodcastItem(newPodcasts, page)
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = RDTheme.color.primary,
            inactiveColor = RDTheme.color.divider,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun NewPodcastItem(newPodcasts: List<PodcastUi>, page: Int, modifier: Modifier = Modifier) {

    Card(
        backgroundColor = RDTheme.color.surface,
        onClick = { },
        shape = RDTheme.shape.cornerBig,
        elevation = RDTheme.elevation.dp8,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .padding(
                start = RDTheme.padding.dp16,
                end = RDTheme.padding.dp16,
                bottom = RDTheme.padding.dp16
            )
    ) {
        CoilShimmerImage(data = newPodcasts[page].image, isLandscape = true)
    }
}
