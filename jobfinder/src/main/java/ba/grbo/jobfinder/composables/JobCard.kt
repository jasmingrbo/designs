package ba.grbo.jobfinder.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.jobfinder.Job
import ba.grbo.jobfinder.R
import ba.grbo.jobfinder.ui.theme.gray
import ba.grbo.jobfinder.ui.theme.inter

@Composable
fun JobCard(
    modifier: Modifier = Modifier,
    job: Job,
    onClicked: (Int) -> Unit,
    onBookmarkJobButtonClicked: (Int) -> Unit
) {
    Card(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.onPrimary
    ) {
        Column(
            modifier = Modifier
                .clickable(
                    onClick = { onClicked(job.id) },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                )
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                EmployerLogo(
                    modifier = Modifier.size(40.dp),
                    backgroundColor = job.logoBackgroundColor,
                    logoResource = job.employerLogoResource
                )
                BookmarkIcon(onClicked = { onBookmarkJobButtonClicked(job.id) })
            }
            VerticalSpacer(12.dp)
            Text(
                text = job.title,
                style = TextStyle(
                    fontFamily = inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = MaterialTheme.colors.secondary
                )
            )
            VerticalSpacer(4.dp)
            Text(
                text = "${job.employer} - ${job.location}",
                style = TextStyle(
                    fontFamily = inter,
                    fontWeight = FontWeight.Normal,
                    fontSize = 9.sp,
                    lineHeight = 11.sp,
                    color = gray
                )
            )
            VerticalSpacer(4.dp)
            Text(
                text = stringResource(
                    R.string.home_hourly_wage_formatter,
                    job.hourlyWageRange.first,
                    job.hourlyWageRange.second
                ),
                style = TextStyle(
                    fontFamily = inter,
                    fontWeight = FontWeight.Normal,
                    fontSize = 9.sp,
                    lineHeight = 11.sp,
                    color = gray
                )
            )
        }
    }
}