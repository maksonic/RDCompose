package ru.maksonic.rdcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.maksonic.rdcompose.navigation.api.navigator.GlobalNavigator
import ru.maksonic.rdcompose.navigation.api.destination.GlobalDestination
import ru.maksonic.rdcompose.navigation.impl.graph.GlobalGraph
import ru.maksonic.rdcompose.shared.theme.MainTheme
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

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                globalNavigator.navController = rememberNavController()
                val navController = globalNavigator.navController
                val isDarkMode = isSystemInDarkTheme()

                Scaffold { padding ->
                    NavHost(
                        navController,
                        startDestination = GlobalDestination.route,
                        modifier = Modifier.padding(padding)
                    ) {
                        globalGraphBuilder.buildNavGraph(
                            navGraphBuilder = this,
                            navController,
                            mutableStateOf(isDarkMode)
                        )
                    }
                }
            }
        }
    }
}