package ru.maksonic.rdcompose.screen.categories.view.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_model.category.category.CategoryUi
import ru.maksonic.rdcompose.shared.ui_widget.component.ImageWithShimmer

/**
 * @Author maksonic on 26.05.2022
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ItemCardCategory(category: CategoryUi, onClick: () -> Unit, modifier: Modifier = Modifier) {
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
        backgroundColor = RDTheme.color.surface,
        onClick = { onClick() },
        shape = RDTheme.shape.cornerBig,
        elevation = RDTheme.elevation.dp8,
    ) {
        Box(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {

            ImageWithShimmer(category.image)

            Column(
                modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(RDTheme.color.shadow)
            ) {
                Text(
                    text = category.name,
                    style = RDTheme.typography.display,
                    color = RDTheme.color.onBackgroundText,
                    maxLines = 1,
                    modifier = modifier.padding(
                        start = RDTheme.padding.dp8,
                        top = RDTheme.padding.dp8
                    )
                )
                Text(
                    text = category.description,
                    style = RDTheme.typography.title,
                    color = RDTheme.color.onBackgroundText,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    modifier = modifier.padding(RDTheme.padding.dp8)
                )
            }
        }
    }
}