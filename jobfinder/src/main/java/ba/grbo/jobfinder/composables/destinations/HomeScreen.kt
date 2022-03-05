package ba.grbo.jobfinder.composables.destinations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ba.grbo.jobfinder.BottomNavBarDestination.HOME
import ba.grbo.jobfinder.composables.BottomNavBar
import ba.grbo.jobfinder.composables.HomeAppBar
import ba.grbo.jobfinder.composables.Searcher
import ba.grbo.jobfinder.composables.VerticalSpacer

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    userName: String,
    contentPadding: PaddingValues,
    onUserButtonClicked: () -> Unit,
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
            HomeAppBar(userName = userName, onUserButtonClicked = onUserButtonClicked)
            VerticalSpacer(20.dp)

            val (searchTerm, onSearchTermChanged) = remember { mutableStateOf("") }
            Searcher(searchTerm = searchTerm, onSearchTermChanged = onSearchTermChanged)
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