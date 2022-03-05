package ba.grbo.jobfinder.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.jobfinder.EmploymentType.FULL
import ba.grbo.jobfinder.Job
import ba.grbo.jobfinder.R
import ba.grbo.jobfinder.Seniority.ENTRY
import ba.grbo.jobfinder.Seniority.JUNIOR
import ba.grbo.jobfinder.Seniority.MEDIOR
import ba.grbo.jobfinder.Seniority.SENIOR
import ba.grbo.jobfinder.ui.theme.inter

@Composable
fun PopularJobCard(
    modifier: Modifier = Modifier,
    job: Job,
    backgroundColor: Color,
    contentColor: Color,
    badgeBackgroundColor: Color,
    badgeContentColor: Color,
    onClicked: (Int) -> Unit,
    onBookmarkJobButtonClicked: (Int) -> Unit
) {
    Card(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .requiredWidth(IntrinsicSize.Max)
                .clickable(
                    onClick = { onClicked(job.id) },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                )
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    modifier = Modifier.size(44.dp),
                    shape = MaterialTheme.shapes.medium,
                    color = job.logoBackgroundColor
                ) {
                    Image(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        painter = painterResource(job.employerLogoResource),
                        contentDescription = stringResource(R.string.home_employer_logo)
                    )
                }

                Text(
                    text = stringResource(
                        R.string.home_hourly_wage_formatter,
                        job.hourlyWageRange.first,
                        job.hourlyWageRange.second
                    ),
                    style = MaterialTheme.typography.body2
                )
            }
            VerticalSpacer(8.dp)
            Text(
                text = job.title,
                style = TextStyle(
                    fontFamily = inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 17.sp
                )
            )
            VerticalSpacer(4.dp)
            Text(
                text = "${job.employer} - ${job.location}",
                style = TextStyle(
                    fontFamily = inter,
                    fontWeight = FontWeight.Normal,
                    fontSize = 11.sp,
                    lineHeight = 13.sp
                )
            )
            VerticalSpacer(12.dp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Chip(
                    text = stringResource(
                        if (job.employmentType == FULL) R.string.common_full_time_label
                        else R.string.common_part_time_label
                    ),
                    backgroundColor = badgeBackgroundColor,
                    contentColor = badgeContentColor,
                    contentPadding = PaddingValues(horizontal = 8.dp)
                )
                HorizontalSpacer(8.dp)
                Chip(
                    text = stringResource(
                        if (job.remote) R.string.common_remote_label
                        else R.string.common_office_label
                    ),
                    backgroundColor = badgeBackgroundColor,
                    contentColor = badgeContentColor,
                    contentPadding = PaddingValues(horizontal = 8.dp)
                )
                HorizontalSpacer(8.dp)
                Chip(
                    text = stringResource(
                        when (job.seniority) {
                            ENTRY -> R.string.common_entry_label
                            JUNIOR -> R.string.common_junior_label
                            MEDIOR -> R.string.common_medior_label
                            SENIOR -> R.string.common_senior_label
                        }
                    ),
                    backgroundColor = badgeBackgroundColor,
                    contentColor = badgeContentColor,
                    contentPadding = PaddingValues(horizontal = 8.dp)
                )
                HorizontalSpacer(36.dp)
                Icon(
                    modifier = Modifier
                        .clickable(
                            onClick = { onBookmarkJobButtonClicked(job.id) },
                            role = Role.Image
                        ),
                    painter = painterResource(R.drawable.ic_bookmark_job),
                    contentDescription = stringResource(R.string.home_bookmark_job)
                )
                HorizontalSpacer(12.dp)
            }
        }
    }
}