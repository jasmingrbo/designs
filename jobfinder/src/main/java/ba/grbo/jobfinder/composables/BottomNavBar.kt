package ba.grbo.jobfinder.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ba.grbo.jobfinder.BottomNavBarDestination
import ba.grbo.jobfinder.BottomNavBarDestination.BOOKMARKED
import ba.grbo.jobfinder.BottomNavBarDestination.HOME
import ba.grbo.jobfinder.BottomNavBarDestination.SETTINGS
import ba.grbo.jobfinder.R
import ba.grbo.jobfinder.ui.theme.eastBay
import ba.grbo.jobfinder.ui.theme.silver
import ba.grbo.jobfinder.ui.theme.white

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    currentDestination: BottomNavBarDestination,
    onHomeButtonClicked: () -> Unit,
    onBookmarkButtonClicked: () -> Unit,
    onSettingsButtonClicked: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = white,
        shape = RectangleShape,
        elevation = 4.dp
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onHomeButtonClicked) {
                Icon(
                    painter = painterResource(R.drawable.ic_home),
                    contentDescription = stringResource(R.string.common_home_icon),
                    tint = if (currentDestination == HOME) eastBay else silver
                )
            }

            IconButton(onClick = onBookmarkButtonClicked) {
                Icon(
                    painter = painterResource(R.drawable.ic_bookmark_home),
                    contentDescription = stringResource(R.string.common_bookmark_icon),
                    tint = if (currentDestination == BOOKMARKED) eastBay else silver
                )
            }

            IconButton(onClick = onSettingsButtonClicked) {
                Icon(
                    painter = painterResource(R.drawable.ic_settings),
                    contentDescription = stringResource(R.string.common_settings_icon),
                    tint = if (currentDestination == SETTINGS) eastBay else silver
                )
            }
        }
    }
}