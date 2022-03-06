package ba.grbo.jobfinder.composables.destinations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import ba.grbo.jobfinder.Job
import ba.grbo.jobfinder.R

@Composable
fun JobScreen(
    modifier: Modifier = Modifier,
    job: Job
) {
    Column(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.job),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}