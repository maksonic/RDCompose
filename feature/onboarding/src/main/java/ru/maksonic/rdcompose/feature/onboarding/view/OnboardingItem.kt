package ru.maksonic.rdcompose.feature.onboarding.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.maksonic.rdcompose.feature.onboarding.model.Model
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.component.CoilSimpleImage

/**
 * @Author maksonic on 23.05.2022
 */
@Composable
internal fun OnboardingItem(
    page: Int,
    model: Model,
    modifier: Modifier = Modifier
) {
    val dp16 = RDTheme.padding.dp16

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        val paddingImage = if (page == 0) RDTheme.padding.dp8 else RDTheme.padding.dp32
        CoilSimpleImage(
            data = model.onboardingList[page].image,
            contentScale = ContentScale.Fit,
            modifier = modifier
                .weight(1f)
                .padding(
                    start = paddingImage,
                    end = paddingImage
                ),
        )
        /* Box(
             modifier
                 .weight(1f)
                 .padding(top = RDTheme.padding.dp24),
             contentAlignment = Alignment.Center
         ) {
             CoilSimpleImage(
                 data = model.onboardingList[page].image,
                 modifier = modifier
                     .fillMaxSize()
                     .padding(start = RDTheme.padding.dp16, end = RDTheme.padding.dp16),
             )
         }*/
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