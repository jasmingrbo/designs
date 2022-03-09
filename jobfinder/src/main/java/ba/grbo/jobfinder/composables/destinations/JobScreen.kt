package ba.grbo.jobfinder.composables.destinations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ba.grbo.jobfinder.Job
import ba.grbo.jobfinder.ui.theme.wildSand

@Composable
fun JobScreen(
    modifier: Modifier = Modifier,
    job: Job
) {
    Layout(
        modifier = modifier,
        content = {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(job.jobImageResource),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                backgroundColor = wildSand,
                elevation = 0.dp
            ) {

            }
        }
    ) { measurables, constraints ->
        val imageWithAppBar = measurables[0].measure(constraints)
        val imageWithAppBarTenthHeight = imageWithAppBar.height / 10
        val jobInfo = measurables[1].measure(
            constraints.copy(
                maxHeight = constraints.maxHeight - imageWithAppBar.height + imageWithAppBarTenthHeight
            )
        )
        layout(width = constraints.maxWidth, height = constraints.maxHeight) {
            imageWithAppBar.placeRelative(x = 0, y = 0)
            jobInfo.placeRelative(
                x = 0,
                y = imageWithAppBar.height - imageWithAppBarTenthHeight
            )
        }
    }
}