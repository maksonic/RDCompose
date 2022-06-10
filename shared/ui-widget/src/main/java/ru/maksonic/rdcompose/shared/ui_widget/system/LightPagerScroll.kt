package ru.maksonic.rdcompose.shared.ui_widget.system

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

/**
 * @Author maksonic on 10.06.2022
 */
@OptIn(ExperimentalPagerApi::class)
fun Modifier.scrollPage(pagerState: PagerState): Modifier = composed {
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
                        if (pagerState.currentPage != pagerState.pageCount.minus(1)) {
                            pagerState.animateScrollToPage(pagerState.currentPage.plus(1))
                        }
                    }
                }
            }
        }
    }
}