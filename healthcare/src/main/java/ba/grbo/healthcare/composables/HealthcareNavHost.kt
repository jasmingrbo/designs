package ba.grbo.healthcare.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ba.grbo.healthcare.OnboardingScreenData
import ba.grbo.healthcare.R
import ba.grbo.healthcare.composables.destinations.Destination.ONBOARDING
import ba.grbo.healthcare.composables.destinations.OnboardingScreen
import ba.grbo.healthcare.ui.theme.lavenderMagenta
import ba.grbo.healthcare.ui.theme.riptide
import ba.grbo.healthcare.ui.theme.royalBlue

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
            val data = remember {
                listOf(
                    OnboardingScreenData(
                        backgroundColor = royalBlue,
                        imageResource = R.drawable.prescription,
                        titleTextResource = R.string.onboarding_consultation_title,
                        descriptionTextResource = R.string.onboarding_consultation_description
                    ),
                    OnboardingScreenData(
                        backgroundColor = riptide,
                        imageResource = R.drawable.note,
                        titleTextResource = R.string.onboarding_ehr_title,
                        descriptionTextResource = R.string.onboarding_ehr_description
                    ),
                    OnboardingScreenData(
                        backgroundColor = lavenderMagenta,
                        imageResource = R.drawable.pills,
                        titleTextResource = R.string.onboarding_medications_title,
                        descriptionTextResource = R.string.onboarding_medications_title
                    )
                )
            }
            OnboardingScreen(
                data = data.first(),
                showSkipButton = true,
                onSkipButtonClicked = {}
            )
        }
    }
}