package ru.maksonic.rdcompose.feature.onboarding.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ru.maksonic.rdcompose.feature.onboarding.model.Msg
import ru.maksonic.rdcompose.feature.onboarding.update.OnboardingViewModel
import ru.maksonic.rdcompose.feature.user_auth.auth_bottom_sheet.AuthBottomSheet
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R.*
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton
import ru.maksonic.rdcompose.shared.ui_widget.button.PrimaryButton

/**
 * @Author maksonic on 23.05.2022
 */
@Composable
fun OnboardingScreen() {
    val viewModel: OnboardingViewModel = hiltViewModel()

    OnboardingScreenUi(viewModel)
}

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalPagerApi::class,
    ExperimentalMaterialApi::class
)
@Composable
private fun OnboardingScreenUi(viewModel: OnboardingViewModel, modifier: Modifier = Modifier) {
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0)
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    BackHandler(enabled = model.value.isShowAuthSheet) {
        sendMsg(Msg.Ui.OnHideAuthSheet(modalBottomSheetState, scope))
    }

    ModalBottomSheetLayout(
        sheetContent = {
            AuthBottomSheet(
                showPrivacy = { sendMsg(Msg.Ui.ShowPrivacy) },
                showTermsOfUse = { sendMsg(Msg.Ui.ShowTermsOfUse) }
            )
        },
        sheetState = modalBottomSheetState,
        sheetBackgroundColor = RDTheme.color.surface,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Scaffold(
            backgroundColor = RDTheme.color.background,
            topBar = {
                TopAppBar(
                    title = {},
                    backgroundColor = RDTheme.color.background,
                    elevation = RDTheme.elevation.elevationDisable,
                    actions = {
                        IconActionButton(onClick = { sendMsg(Msg.Ui.OnSkipOnboarding) }) {
                            Icon(
                                painter = painterResource(id = drawable.ic_round_close_24),
                                tint = RDTheme.color.controlNormal,
                                contentDescription = ""
                            )
                        }
                    }
                )
            },
            modifier = modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {

            Column(
                modifier.padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CompositionLocalProvider(LocalOverScrollConfiguration provides null) {

                    HorizontalPager(
                        count = model.value.onboardingList.size,
                        state = pagerState,
                        userScrollEnabled = false,
                        modifier = modifier
                            .weight(1.0f)
                    ) { page ->
                        OnboardingItem(page, model.value, pagerState)
                    }
                }
                Spacer(modifier.size(RDTheme.padding.dp16))

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = RDTheme.color.primary,
                    inactiveColor = RDTheme.color.divider,
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier.size(RDTheme.padding.dp16))

                PrimaryButton(
                    action = {
                        sendMsg(Msg.Ui.OnShowAuthSheet(modalBottomSheetState, scope))
                    },
                    title = stringResource(string.btn_title_create_account)
                )

                Spacer(modifier.size(RDTheme.padding.dp16))
            }
        }
    }
}