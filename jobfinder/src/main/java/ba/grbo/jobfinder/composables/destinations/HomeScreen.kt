package ba.grbo.jobfinder.composables.destinations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.jobfinder.BottomNavBarDestination.HOME
import ba.grbo.jobfinder.Job
import ba.grbo.jobfinder.JobCategory
import ba.grbo.jobfinder.R
import ba.grbo.jobfinder.composables.Chip
import ba.grbo.jobfinder.composables.BottomNavBar
import ba.grbo.jobfinder.composables.HomeAppBar
import ba.grbo.jobfinder.composables.HorizontalSpacer
import ba.grbo.jobfinder.composables.PopularJobCard
import ba.grbo.jobfinder.composables.Searcher
import ba.grbo.jobfinder.composables.VerticalSpacer
import ba.grbo.jobfinder.ui.theme.alto
import ba.grbo.jobfinder.ui.theme.concrete
import ba.grbo.jobfinder.ui.theme.gray
import ba.grbo.jobfinder.ui.theme.inter
import ba.grbo.jobfinder.ui.theme.mineShaft

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    userName: String,
    contentPadding: PaddingValues,
    popularJobs: List<Job>,
    jobCategories: List<String>,
    onUserButtonClicked: () -> Unit,
    onFilterButtonClicked: () -> Unit,
    onJobClicked: (Int) -> Unit,
    onBookmarkJobButtonClicked: (Int) -> Unit,
    onJobCategoryClicked: (JobCategory) -> Unit,
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
            HomeAppBar(
                modifier = Modifier.padding(horizontal = 24.dp),
                userName = userName,
                onUserButtonClicked = onUserButtonClicked
            )
            VerticalSpacer(20.dp)

            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
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

            VerticalSpacer(20.dp)
            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = stringResource(R.string.home_most_popular_label),
                style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.SemiBold),
                color = mineShaft
            )
            VerticalSpacer(12.dp)
            LazyRow(
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(popularJobs, key = { job -> job.id }) { job ->
                    PopularJobCard(
                        job = job,
                        backgroundColor = if (job.id % 2 != 0) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary,
                        contentColor = if (job.id % 2 != 0) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary,
                        badgeBackgroundColor = if (job.id % 2 != 0) MaterialTheme.colors.onPrimary else concrete,
                        badgeContentColor = MaterialTheme.colors.primary,
                        onClicked = onJobClicked,
                        onBookmarkJobButtonClicked = onBookmarkJobButtonClicked
                    )
                }
            }
            VerticalSpacer(20.dp)
            LazyRow(
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(jobCategories, key = { category -> category }) { category ->
                    Chip(
                        text = category,
                        selected = category == "All Jobs",
                        backgroundColor = alto,
                        contentColor = gray,
                        shape = MaterialTheme.shapes.medium,
                        contentPadding = PaddingValues(horizontal = 12.dp),
                        textStyle = TextStyle(
                            fontFamily = inter,
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp,
                            lineHeight = 16.sp
                        ),
                        onClicked = { jobCategory ->
                            onJobCategoryClicked(JobCategory.fromValue(jobCategory))
                        }
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