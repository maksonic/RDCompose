package ru.maksonic.rdcompose.feature.user_auth.auth_bottom_sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import ru.maksonic.rdcompose.feature.user_auth.R
import ru.maksonic.rdcompose.shared.theme.RDTheme

/**
 * @author maksonic on 16.04.2022
 */
private const val privacyTag = "privacy_clickable_text"
private const val termsTag = "terms_clickable_text"

@Composable
internal fun AnnotatedClickableText(
    showPrivacy: (String) -> Unit,
    showTermsOfUse: (String) -> Unit,
    privacyKey: String,
    termsOfUseKey: String,
    modifier: Modifier = Modifier
) {


    Column(
        modifier
            .fillMaxWidth()
            .padding(start = RDTheme.padding.dp16, end = RDTheme.padding.dp16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val annotatedString = buildAnnotatedString {
            append(stringResource(R.string.auth_caption_part_one))
            pushStringAnnotation(tag = termsTag, annotation = termsTag)
            withStyle(
                style = SpanStyle(
                    color = RDTheme.color.primaryText,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(stringResource(id = R.string.auth_caption_clickable_part_one))
            }
            pop()
            append(stringResource(R.string.auth_caption_part_two))
            pushStringAnnotation(tag = privacyTag, annotation = privacyTag)
            withStyle(
                style = SpanStyle(
                    color = RDTheme.color.primaryText,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(stringResource(R.string.auth_caption_clickable_part_two))
            }
            pop()
        }

        ClickableText(
            text = annotatedString,
            style = TextStyle(color = RDTheme.color.secondaryText, textAlign = TextAlign.Center),
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = termsTag, start = offset, end = offset)
                    .firstOrNull()?.let { showTermsOfUse(termsOfUseKey) }

                annotatedString.getStringAnnotations(tag = privacyTag, start = offset, end = offset)
                    .firstOrNull()?.let { showPrivacy(privacyKey) }
            }
        )
    }
}
