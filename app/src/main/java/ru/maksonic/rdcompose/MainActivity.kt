package ru.maksonic.rdcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.maksonic.rdcompose.core.store.AppTheme
import ru.maksonic.rdcompose.navigation.api.destination.GlobalDestination
import ru.maksonic.rdcompose.navigation.api.navigator.GlobalNavigator
import ru.maksonic.rdcompose.navigation.impl.graph.GlobalGraph
import ru.maksonic.rdcompose.shared.theme.theme.HighContrastTheme
import ru.maksonic.rdcompose.shared.theme.theme.MainTheme
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.theme.theme.SystemComponentsColor
import javax.inject.Inject

/**
 * @Author maksonic on 23.05.2022
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var globalNavigator: GlobalNavigator

    @Inject
    lateinit var globalGraphBuilder: GlobalGraph

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                globalNavigator.navController = rememberNavController()
                val navController = globalNavigator.navController
                val systemTheme = isSystemInDarkTheme()
                val appTheme = viewModel.themeState.collectAsState()

                val rdComposeTheme: @Composable (
                    content: @Composable () -> Unit
                ) -> Unit = when (appTheme.value) {
                    AppTheme.SYSTEM -> { content -> MainTheme(systemTheme, content = content) }
                    AppTheme.DARK -> { content -> MainTheme(darkTheme = true, content) }
                    AppTheme.LIGHT -> { content -> MainTheme(darkTheme = false, content = content) }
                    AppTheme.HIGH_CONTRAST -> { content -> HighContrastTheme(content = content) }
                }
                rdComposeTheme.invoke {
                    val backgroundColor = RDTheme.color.background
                    SystemComponentsColor(
                        isDarkMode = systemTheme,
                        themeType = appTheme,
                        actualBackgroundColor = backgroundColor
                    )
                    Scaffold { padding ->
                        NavHost(
                            navController,
                            startDestination = GlobalDestination.route,
                            modifier = Modifier.padding(padding)
                        ) {
                            globalGraphBuilder.buildNavGraph(
                                navGraphBuilder = this,
                                navController,
                            )
                        }
                    }
                }
            }
        }
    }
}