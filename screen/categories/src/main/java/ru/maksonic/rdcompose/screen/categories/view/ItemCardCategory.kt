package ru.maksonic.rdcompose.screen.categories.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.maksonic.rdcompose.screen.categories.model.Model
import ru.maksonic.rdcompose.shared.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_model.category.CategoryUi
import ru.maksonic.rdcompose.shared.ui_widget.R

/**
 * @Author maksonic on 26.05.2022
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemCardCategory(category: CategoryUi, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = RDTheme.padding.dp32,
                end = RDTheme.padding.dp32,
                top = RDTheme.padding.dp16,
                bottom = RDTheme.padding.dp16
            )
            .aspectRatio(1f),
        onClick = { onClick() },
        shape = RDTheme.shape.cornerBig,
        elevation = RDTheme.elevation.dp8,
    ) {
        Box(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(category.image)
                    .build(),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.podcast_image),
                contentDescription = "",
                modifier = modifier.fillMaxSize()
            )
            Column(
                modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(RDTheme.color.shadow)
            ) {
                Text(
                    text = category.name,
                    style = RDTheme.typography.display,
                    color = RDTheme.color.onPrimary,
                    maxLines = 1,
                    modifier = modifier.padding(
                        start = RDTheme.padding.dp8,
                        top = RDTheme.padding.dp8
                    )
                )
                Text(
                    text = category.description,
                    style = RDTheme.typography.title,
                    color = RDTheme.color.onPrimary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    modifier = modifier.padding(RDTheme.padding.dp8)
                )
            }
        }
    }
}