package ba.grbo.healthcare.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ba.grbo.healthcare.composables.destinations.Destination
import ba.grbo.healthcare.composables.destinations.Destination.*
import ba.grbo.healthcare.composables.destinations.OnboardingScreen

@Composable
fun HealthcareNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ONBOARDING.route
    ) {
        composable(ONBOARDING.route) {
            OnboardingScreen()
        }
    }
}