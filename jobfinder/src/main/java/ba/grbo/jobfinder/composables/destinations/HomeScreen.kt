package ba.grbo.jobfinder.composables.destinations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ba.grbo.jobfinder.BottomNavBarDestination.HOME
import ba.grbo.jobfinder.composables.BottomNavBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    onHomeButtonClicked: () -> Unit,
    onBookmarkButtonClicked: () -> Unit,
    onSettingsButtonClicked: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .weight(1f)
                .padding(contentPadding)
        ) {

        }
        BottomNavBar(
            modifier = Modifier.height(68.dp),
            currentDestination = HOME,
            onHomeButtonClicked = onHomeButtonClicked,
            onBookmarkButtonClicked = onBookmarkButtonClicked,
            onSettingsButtonClicked = onSettingsButtonClicked
        )
    }
}