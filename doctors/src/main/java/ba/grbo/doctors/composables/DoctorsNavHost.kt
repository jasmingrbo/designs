package ba.grbo.doctors.composables

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ba.grbo.doctors.Doctors
import ba.grbo.doctors.composables.destinations.Destination.DOCTOR
import ba.grbo.doctors.composables.destinations.Destination.DOCTORS
import ba.grbo.doctors.composables.destinations.DoctorScreen
import ba.grbo.doctors.composables.destinations.DoctorsScreen

@Composable
fun DoctorsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    showNotImplementedToast: () -> Unit
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = DOCTORS.route
    ) {
        composable(DOCTORS.route) {
            val focusManager = LocalFocusManager.current
            DoctorsScreen(
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { if (tryAwaitRelease()) focusManager.clearFocus() }
                    )
                },
                doctors = Doctors.value,
                onDoctorClicked = { doctor -> navController.navigate("${DOCTOR.route}/${doctor.id}") },
                onDoctorAvailabilityButtonClicked = {
                    showNotImplementedToast()
                    focusManager.clearFocus()
                },
                onMenuButtonClicked = {
                    showNotImplementedToast()
                    focusManager.clearFocus()
                },
                onUserButtonClicked = {
                    showNotImplementedToast()
                    focusManager.clearFocus()
                },
                onCategoryButtonClicked = {
                    showNotImplementedToast()
                    focusManager.clearFocus()
                },
                onViewAllButtonClicked = {
                    showNotImplementedToast()
                    focusManager.clearFocus()
                }
            )
        }

        composable(
            route = "${DOCTOR.route}/{doctorId}",
            arguments = listOf(navArgument(name = "doctorId") { type = NavType.IntType })
        ) { backStackEntry ->
            val doctorId = backStackEntry.arguments?.getInt("doctorId")
                ?: throw IllegalStateException("doctorId is not passed")
            val doctor = Doctors.value.find { doctor -> doctor.id == doctorId }
                ?: throw IllegalStateException("doctor with the id $doctorId not found")

            DoctorScreen(
                doctor = doctor,
                onBackButtonClicked = { navController.popBackStack() },
                onBookmarkButtonClicked = { showNotImplementedToast() },
                onMessageButtonClicked = { showNotImplementedToast() },
                onMakeAppointmentButtonClicked = { showNotImplementedToast() }
            )
        }
    }
}