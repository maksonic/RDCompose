package ru.maksonic.rdcompose.shared.ui_widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 31.05.2022
 */
@Composable
fun ImageWithShimmer(source: Any?, modifier: Modifier = Modifier) {
    val innerModifier = Modifier
    Box(modifier.wrapContentSize()) {
        GlideImage(
            imageModel = source,
            shimmerParams = ShimmerParams(
                baseColor = RDTheme.color.divider,
                highlightColor = RDTheme.color.surface,
                durationMillis = 350,
                dropOff = 0.65f,
                tilt = 20f
            ),
            previewPlaceholder = R.drawable.podcast_image,
            error = painterResource(id = R.drawable.podcast_image),
            modifier = innerModifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
    }

}