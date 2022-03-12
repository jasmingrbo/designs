package ba.grbo.jobfinder.composables.destinations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.jobfinder.EmploymentType
import ba.grbo.jobfinder.Job
import ba.grbo.jobfinder.R
import ba.grbo.jobfinder.Seniority
import ba.grbo.jobfinder.composables.Chip
import ba.grbo.jobfinder.composables.EmployerLogo
import ba.grbo.jobfinder.composables.HorizontalSpacer
import ba.grbo.jobfinder.composables.VerticalSpacer
import ba.grbo.jobfinder.ui.theme.eastBay
import ba.grbo.jobfinder.ui.theme.inter
import ba.grbo.jobfinder.ui.theme.white
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
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        EmployerLogo(
                            modifier = Modifier.size(76.dp),
                            backgroundColor = job.logoBackgroundColor,
                            logoPadding = PaddingValues(8.dp),
                            logoResource = job.employerLogoResource
                        )
                        HorizontalSpacer(12.dp)
                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                text = job.title,
                                style = TextStyle(
                                    fontFamily = inter,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp,
                                    lineHeight = 18.sp
                                )
                            )
                            VerticalSpacer(4.dp)
                            Text(
                                text = "${job.employer} - ${job.location}",
                                style = TextStyle(
                                    fontFamily = inter,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp
                                )
                            )
                            VerticalSpacer(8.dp)
                            Row {
                                Chip(
                                    text = stringResource(
                                        if (job.employmentType == EmploymentType.FULL) R.string.common_full_time_label
                                        else R.string.common_part_time_label
                                    ),
                                    backgroundColor = eastBay,
                                    contentColor = white,
                                    contentPadding = PaddingValues(
                                        horizontal = 12.dp,
                                        vertical = 2.dp
                                    )
                                )
                                HorizontalSpacer(12.dp)
                                Chip(
                                    text = stringResource(
                                        if (job.remote) R.string.common_remote_label
                                        else R.string.common_office_label
                                    ),
                                    backgroundColor = eastBay,
                                    contentColor = white,
                                    contentPadding = PaddingValues(
                                        horizontal = 12.dp,
                                        vertical = 2.dp
                                    )
                                )
                                HorizontalSpacer(12.dp)
                                Chip(
                                    text = stringResource(
                                        when (job.seniority) {
                                            Seniority.ENTRY -> R.string.common_entry_label
                                            Seniority.JUNIOR -> R.string.common_junior_label
                                            Seniority.MEDIOR -> R.string.common_medior_label
                                            Seniority.SENIOR -> R.string.common_senior_label
                                        }
                                    ),
                                    backgroundColor = eastBay,
                                    contentColor = white,
                                    contentPadding = PaddingValues(
                                        horizontal = 12.dp,
                                        vertical = 2.dp
                                    )
                                )
                            }
                        }
                    }
                }
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