package ba.grbo.healthcare

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ba.grbo.healthcare.composables.HealthcareNavHost
import ba.grbo.healthcare.ui.theme.HealthcareTheme

class HealthcareActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            HealthcareTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { HealthcareNavHost(showNotImplementedToast = ::showNotImplementedToast) }
            }
        }
    }

    private fun showNotImplementedToast() {
        Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show()
    }
}