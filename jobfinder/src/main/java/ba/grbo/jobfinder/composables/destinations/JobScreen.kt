package ba.grbo.jobfinder.composables.destinations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ba.grbo.jobfinder.Job

@Composable
fun JobScreen(
    modifier: Modifier = Modifier,
    job: Job
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Hi from job: $job")
    }
}