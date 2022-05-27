package ru.maksonic.rdcompose.feature.user_auth.auth_bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.maksonic.rdcompose.feature.user_auth.R
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.IndicatorBottomSheet

/**
 * @Author maksonic on 24.05.2022
 */
@Composable
fun AuthBottomSheet(
) {
    val scope = rememberCoroutineScope()

    AuthBottomSheetUi()
}

@Composable
private fun AuthBottomSheetUi(modifier: Modifier = Modifier) {

    Box(
        modifier
            .wrapContentSize()
            .defaultMinSize(minHeight = 1.dp)
            .background(RDTheme.color.surface)
            .navigationBarsPadding(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier
                .wrapContentSize()
                .defaultMinSize(minHeight = 1.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IndicatorBottomSheet()

            AuthItem(
                icon = ru.maksonic.rdcompose.shared.ui_widget.R.drawable.ic_google,
                title = stringResource(id = R.string.btn_title_auth_google),
                action = {}
            )

            AuthItem(
                icon = ru.maksonic.rdcompose.shared.ui_widget.R.drawable.ic_phone,
                title = stringResource(id = R.string.btn_title_auth_sign_up),
                action = { }
            )

            AuthItem(
                icon = ru.maksonic.rdcompose.shared.ui_widget.R.drawable.ic_login,
                title = stringResource(id = R.string.btn_title_auth_log_in),
                action = {}
            )
            Spacer(modifier = Modifier.height(RDTheme.padding.dp16))

            val privacyKey = "privacy_policy_scr_data_key"
            val termsOfUseKey = "terms_of_use_scr_data_key"
            AnnotatedClickableText(
                showPrivacy = {},
                showTermsOfUse = { },
                termsOfUseKey = termsOfUseKey,
                privacyKey = privacyKey
            )

            Spacer(modifier.height(RDTheme.padding.dp32))
        }
    }
}
