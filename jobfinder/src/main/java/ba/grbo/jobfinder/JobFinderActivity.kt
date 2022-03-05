package ba.grbo.jobfinder

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import ba.grbo.jobfinder.composables.JobFinderNavHost
import ba.grbo.jobfinder.ui.theme.JobFinderTheme

class JobFinderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobFinderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    JobFinderNavHost(showNotImplementedToast = ::showNotImplementedToast)
                }
            }
        }
    }

    private fun showNotImplementedToast() {
        Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show()
    }
}