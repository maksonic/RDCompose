package ru.maksonic.rdcompose.feature.privacy

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.topbar.TopAppBarNormal

/**
 * @Author maksonic on 31.05.2022
 */
@Composable
fun PrivacyScreen() {
    val viewModel: PrivacyViewModel = hiltViewModel()
    PrivacyScreenUi(viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyScreenUi(viewModel: PrivacyViewModel, modifier: Modifier = Modifier) {
    val state = viewModel.screenState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBarNormal(
                title = state.value.title,
                backPressed = { viewModel.backPressed() })
        },
        containerColor = RDTheme.color.background,
        modifier = modifier.systemBarsPadding()
    ) { padding ->
        LazyColumn(modifier.fillMaxSize().padding(padding)) {
            item {
                Text(
                    text = state.value.body,
                    style = RDTheme.typography.body,
                    color = RDTheme.color.primaryText,
                    modifier = modifier.fillMaxSize().padding(RDTheme.padding.dp16)
                )
            }
        }
    }
}