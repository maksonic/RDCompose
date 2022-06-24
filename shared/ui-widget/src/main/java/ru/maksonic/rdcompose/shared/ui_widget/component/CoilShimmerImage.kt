package ru.maksonic.rdcompose.shared.ui_widget.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.*
import coil.disk.DiskCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Precision
import coil.size.Size
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R

/**
 * @Author maksonic on 23.06.2022
 */
data class PlaceholderImage(
    val portrait: Int,
    val landscape: Int
)

@Composable
fun CoilSimpleImage(
    modifier: Modifier = Modifier,
    data: Any?,
    contentScale: ContentScale = ContentScale.Crop,
) {
    AsyncImage(
        model = data,
        contentDescription = "",
        contentScale = contentScale,
        modifier = modifier
    )
}

@Composable
fun CoilShimmerImage(
    modifier: Modifier = Modifier,
    data: Any?,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .respectCacheHeaders(false)
        .diskCachePolicy(CachePolicy.ENABLED)
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("image_cache"))
                .build()
        }
        .build()

    SubcomposeAsyncImage(
        model = data,
        imageLoader = imageLoader,
        contentDescription = null,
        contentScale = contentScale,
        alignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize(1f)
            .defaultMinSize(1.dp)
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> Box(
                modifier
                    .fillMaxSize(1f)
                    .placeholder(
                        visible = true,
                        color = RDTheme.color.divider,
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = RDTheme.color.surface)
                    )
            )
            is AsyncImagePainter.State.Error -> Image(
                painterResource(RDTheme.image.placeholder),
                contentScale = contentScale,
                contentDescription = ""
            )
            else -> SubcomposeAsyncImageContent()
        }
    }
}