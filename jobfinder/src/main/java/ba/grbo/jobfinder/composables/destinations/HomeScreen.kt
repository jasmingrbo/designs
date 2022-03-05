package ba.grbo.jobfinder.composables.destinations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ba.grbo.jobfinder.BottomNavBarDestination.HOME
import ba.grbo.jobfinder.R
import ba.grbo.jobfinder.composables.BottomNavBar
import ba.grbo.jobfinder.composables.HomeAppBar
import ba.grbo.jobfinder.composables.HorizontalSpacer
import ba.grbo.jobfinder.composables.Searcher
import ba.grbo.jobfinder.composables.VerticalSpacer

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    userName: String,
    contentPadding: PaddingValues,
    onUserButtonClicked: () -> Unit,
    onFilterButtonClicked: () -> Unit,
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

            Row(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
            ) {
                val (searchTerm, onSearchTermChanged) = remember { mutableStateOf("") }
                Searcher(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    searchTerm = searchTerm,
                    onSearchTermChanged = onSearchTermChanged
                )
                HorizontalSpacer(8.dp)
                Button(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f),
                    shape = MaterialTheme.shapes.medium,
                    onClick = onFilterButtonClicked,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onPrimary
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_filter),
                        contentDescription = null
                    )
                }
            }
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