package ru.maksonic.rdcompose.screen.home.view.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.rippleClickable

/**
 * @Author maksonic on 10.06.2022
 */
@Composable
internal fun ItemHeader(
    modifier: Modifier = Modifier,
    title: String,
    showActionBtn: Boolean = true,
    action: () -> Unit = {},
) {
    Row(
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(RDTheme.padding.dp8),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            style = RDTheme.typography.header,
            color = RDTheme.color.primaryText,
            modifier = modifier
                .padding(start = RDTheme.padding.dp8)
                .weight(1f)
        )
        if (showActionBtn) {
            Text(
                text = stringResource(R.string.btn_title_more_podcasts),
                style = RDTheme.typography.caption.copy(
                    fontSize = 18.sp,
                    color = RDTheme.color.secondaryText
                ),
                modifier = modifier
                    .clip(RDTheme.shape.cornerNormal)
                    .rippleClickable { action.invoke() }
                    .padding(RDTheme.padding.dp8)

            )
        }
    }
}