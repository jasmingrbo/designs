package ba.grbo.jobfinder.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ba.grbo.jobfinder.composables.destinations.Destination.HOME
import ba.grbo.jobfinder.composables.destinations.Destination.START
import ba.grbo.jobfinder.composables.destinations.HomeScreen
import ba.grbo.jobfinder.composables.destinations.StartScreen
import ba.grbo.jobfinder.ui.theme.white
import ba.grbo.jobfinder.ui.theme.wildSand
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun JobFinderNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    showNotImplementedToast: () -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = START.route
    ) {
        composable(START.route) {
            val systemUiController = rememberSystemUiController()
            SideEffect { systemUiController.setSystemBarsColor(wildSand) }

            StartScreen(
                modifier = Modifier.padding(horizontal = 20.dp),
                onGetStartedButtonClicked = {
                    navController.navigate(HOME.route) {
                        popUpTo(START.route) { inclusive = true }
                    }
                }
            )
        }

        composable(HOME.route) {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(wildSand)
                systemUiController.setNavigationBarColor(white)
            }

            HomeScreen(
                userName = "Gustanto",
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 24.dp),
                onFilterButtonClicked = showNotImplementedToast,
                onUserButtonClicked = showNotImplementedToast,
                onHomeButtonClicked = showNotImplementedToast,
                onBookmarkButtonClicked = showNotImplementedToast,
                onSettingsButtonClicked = showNotImplementedToast
            )
        }
    }
}