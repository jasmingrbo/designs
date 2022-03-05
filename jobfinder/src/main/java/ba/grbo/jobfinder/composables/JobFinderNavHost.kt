package ba.grbo.jobfinder.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ba.grbo.jobfinder.composables.destinations.Destination.START
import ba.grbo.jobfinder.composables.destinations.StartScreen
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
                onGetStartedButtonClicked = showNotImplementedToast
            )
        }
    }
}