package ba.grbo.jobfinder.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ba.grbo.jobfinder.JobCategory
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
                contentPadding = PaddingValues(top = 24.dp),
                popularJobs = popularJobs,
                jobs = jobs,
                jobCategories = JobCategory.values().map(JobCategory::value),
                onJobClicked = { jobId -> navController.navigate("${JOB.route}/$jobId") },
                onBookmarkJobButtonClicked = { showNotImplementedToast() },
                onJobCategoryClicked = { showNotImplementedToast() },
                onFilterButtonClicked = showNotImplementedToast,
                onUserButtonClicked = showNotImplementedToast,
                onHomeButtonClicked = showNotImplementedToast,
                onBookmarkButtonClicked = showNotImplementedToast,
                onSettingsButtonClicked = showNotImplementedToast
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

            JobScreen(job = job)
        }
    }
}