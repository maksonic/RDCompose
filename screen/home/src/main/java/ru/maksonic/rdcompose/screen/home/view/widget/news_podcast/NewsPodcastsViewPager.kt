package ru.maksonic.rdcompose.screen.home.view.widget.news_podcast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.view.widget.stories.ItemStoryPager
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.ImageWithShimmer
import ru.maksonic.rdcompose.shared.ui_widget.system.OverscrollEffect
import ru.maksonic.rdcompose.shared.ui_widget.system.scrollPage

/**
 * @Author maksonic on 10.06.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsPodcastsViewPager(model: Model, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(initialPage = 0)

    val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OverscrollEffect {
            HorizontalPager(
                count = model.newPodcasts.size,
                state = pagerState,
                userScrollEnabled = false,
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ) { page ->
                NewPodcastItem(model, pagerState, page)
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = RDTheme.color.primary,
            inactiveColor = RDTheme.color.divider,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun NewPodcastItem(model: Model, pagerState: PagerState, page: Int, modifier: Modifier = Modifier) {

    Card(
        backgroundColor = RDTheme.color.surface,
        onClick = { },
        shape = RDTheme.shape.cornerBig,
        elevation = RDTheme.elevation.dp8,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f/9f)
            .padding(RDTheme.padding.dp16)
            .scrollPage(pagerState)

    ) {
        ImageWithShimmer(source = model.newPodcasts[page].image)
    }
}