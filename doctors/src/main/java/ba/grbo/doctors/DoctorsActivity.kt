package ba.grbo.doctors

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import ba.grbo.doctors.composables.DoctorsNavHost
import ba.grbo.doctors.ui.theme.DoctorsTheme

class DoctorsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoctorsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { DoctorsNavHost(showNotImplementedToast = ::showNotImplementedToast) }
            }
        }
    }

    private fun showNotImplementedToast() {
        Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show()
    }
}