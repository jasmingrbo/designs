package ba.grbo.jobfinder.composables

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ba.grbo.jobfinder.JobCategory
import ba.grbo.jobfinder.Tab
import ba.grbo.jobfinder.composables.destinations.Destination.HOME
import ba.grbo.jobfinder.composables.destinations.Destination.JOB
import ba.grbo.jobfinder.composables.destinations.Destination.START
import ba.grbo.jobfinder.composables.destinations.HomeScreen
import ba.grbo.jobfinder.composables.destinations.JobScreen
import ba.grbo.jobfinder.composables.destinations.StartScreen
import ba.grbo.jobfinder.jobs
import ba.grbo.jobfinder.popularJobs
import ba.grbo.jobfinder.ui.theme.white
import ba.grbo.jobfinder.ui.theme.wildSand
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun JobFinderNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    showNotImplementedToast: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val inset = LocalWindowInsets.current
    val statusBarHeight = with(LocalDensity.current) { inset.statusBars.top.toDp() }
    val navigationBarHeight = with(LocalDensity.current) { inset.navigationBars.bottom.toDp() }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = START.route
    ) {
        composable(START.route) {
            SideEffect { systemUiController.setSystemBarsColor(wildSand) }

            StartScreen(
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = statusBarHeight,
                    bottom = navigationBarHeight
                ),
                onGetStartedButtonClicked = {
                    navController.navigate(HOME.route) {
                        popUpTo(START.route) { inclusive = true }
                    }
                }
            )
        }

        composable(HOME.route) {
            SideEffect {
                systemUiController.setStatusBarColor(wildSand)
                systemUiController.setNavigationBarColor(white)
            }

            val focusManager = LocalFocusManager.current
            HomeScreen(
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = { if (tryAwaitRelease()) focusManager.clearFocus() }
                        )
                    }
                    .padding(
                        top = statusBarHeight + 24.dp,
                        bottom = navigationBarHeight
                    ),
                userName = "Gustanto",
                popularJobs = popularJobs,
                jobs = jobs,
                jobCategories = JobCategory.values().map(JobCategory::value),
                onJobClicked = { jobId ->
                    focusManager.clearFocus()
                    navController.navigate("${JOB.route}/$jobId")
                },
                onBookmarkJobButtonClicked = {
                    showNotImplementedToast()
                    focusManager.clearFocus()
                },
                onJobCategoryClicked = {
                    showNotImplementedToast()
                    focusManager.clearFocus()
                },
                onFilterButtonClicked = {
                    showNotImplementedToast()
                    focusManager.clearFocus()
                },
                onUserButtonClicked = {
                    showNotImplementedToast()
                    focusManager.clearFocus()
                },
                onHomeButtonClicked = {
                    showNotImplementedToast()
                    focusManager.clearFocus()
                },
                onBookmarkButtonClicked = {
                    showNotImplementedToast()
                    focusManager.clearFocus()
                },
                onSettingsButtonClicked = {
                    showNotImplementedToast()
                    focusManager.clearFocus()
                },
                onListScrolled = { focusManager.clearFocus() }
            )
        }

        composable(
            route = "${JOB.route}/{jobId}",
            arguments = listOf(navArgument(name = "jobId") { type = NavType.IntType })
        ) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getInt("jobId")
                ?: throw IllegalStateException("jobId is not passed")
            val job = (popularJobs + jobs).find { job -> job.id == jobId }
                ?: throw IllegalStateException("job with the id $jobId not found")

            SideEffect { systemUiController.setStatusBarColor(Color.Transparent) }
            JobScreen(
                modifier = Modifier.padding(bottom = navigationBarHeight),
                job = job,
                onBackButtonClicked = { navController.popBackStack() },
                selectedTab = Tab.DESCRIPTION,
                onTabClicked = { showNotImplementedToast() }
            )
        }
    }
}