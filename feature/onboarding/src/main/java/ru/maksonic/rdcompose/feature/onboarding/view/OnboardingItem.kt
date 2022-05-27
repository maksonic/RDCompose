package ru.maksonic.rdcompose.feature.onboarding.view

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch
import ru.maksonic.rdcompose.feature.onboarding.model.Model
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 23.05.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun OnboardingItem(
    page: Int,
    model: Model,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val dp16 = RDTheme.padding.dp16

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .scrollPage(pagerState, 3)
    ) {
        Box(
            modifier
                .weight(1f)
                .padding(top = RDTheme.padding.dp24),
            contentAlignment = Alignment.Center
        ) {
            GlideImage(
                imageModel = model.onboardingList[page].image,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = RDTheme.padding.dp16, end = RDTheme.padding.dp16),
                contentDescription = ""
            )
        }
        Spacer(modifier.size(dp16))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(start = dp16, end = dp16)
        ) {
            Text(
                text = model.onboardingList[page].title,
                style = RDTheme.typography.display,
                color = RDTheme.color.primaryText,
                textAlign = TextAlign.Center
            )
            Spacer(modifier.size(RDTheme.padding.dp16))
            Text(
                text = model.onboardingList[page].description,
                fontStyle = FontStyle.Normal,
                color = RDTheme.color.primaryText,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier.size(dp16))
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
private fun Modifier.scrollPage(pagerState: PagerState, maxPageSize: Int): Modifier = composed {
    val scope = rememberCoroutineScope()

    this.pointerInput(Unit) {
        detectDragGestures { _, dragAmount ->
            when {
                dragAmount.x > 0 && dragAmount.x.dp > 10.dp -> {
                    scope.launch {
                        if (pagerState.currentPage > 0) {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage.minus(1),
                            )
                        }
                    }
                }
                dragAmount.x < 0 && dragAmount.x.dp < 10.dp -> {
                    scope.launch {
                        if (pagerState.currentPage != maxPageSize) {
                            pagerState.animateScrollToPage(pagerState.currentPage.plus(1))
                        }
                    }
                }
            }
        }
    }
}