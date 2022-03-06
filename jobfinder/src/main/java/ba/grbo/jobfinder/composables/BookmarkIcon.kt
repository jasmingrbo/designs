package ba.grbo.jobfinder.composables

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import ba.grbo.jobfinder.R

@Composable
fun BookmarkIcon(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    Icon(
        modifier = modifier.clickable(
            onClick = onClicked,
            role = Role.Image
        ),
        painter = painterResource(R.drawable.ic_bookmark_job),
        contentDescription = stringResource(R.string.home_bookmark_job)
    )
}