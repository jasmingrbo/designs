package ba.grbo.jobfinder.composables.destinations

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.jobfinder.EmploymentType
import ba.grbo.jobfinder.Job
import ba.grbo.jobfinder.R
import ba.grbo.jobfinder.Seniority
import ba.grbo.jobfinder.Tab
import ba.grbo.jobfinder.composables.Chip
import ba.grbo.jobfinder.composables.EmployerLogo
import ba.grbo.jobfinder.composables.HorizontalSpacer
import ba.grbo.jobfinder.composables.VerticalSpacer
import ba.grbo.jobfinder.ui.theme.eastBay
import ba.grbo.jobfinder.ui.theme.gallery
import ba.grbo.jobfinder.ui.theme.gray
import ba.grbo.jobfinder.ui.theme.inter
import ba.grbo.jobfinder.ui.theme.mineShaft
import ba.grbo.jobfinder.ui.theme.white
import ba.grbo.jobfinder.ui.theme.wildSand
import com.google.accompanist.insets.LocalWindowInsets
import kotlin.text.Typography.bullet

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun JobScreen(
    modifier: Modifier = Modifier,
    job: Job,
    selectedTab: Tab,
    onBackButtonClicked: () -> Unit,
    onTabClicked: (Tab) -> Unit,
    onApplyNowButtonClicked: (Int) -> Unit,
    onBookmarkJobButtonClicked: (Int) -> Unit
) {
    Layout(
        modifier = modifier,
        content = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(job.jobImageResource),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )

                val inset = LocalWindowInsets.current
                val statusBarHeight = with(LocalDensity.current) { inset.statusBars.top.toDp() }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = statusBarHeight + 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        IconButton(
                            modifier = Modifier.padding(start = 4.dp),
                            onClick = onBackButtonClicked
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_arrow_back),
                                contentDescription = stringResource(R.string.job_back_arrow),
                                tint = white
                            )
                        }
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.job_job_detail),
                            textAlign = TextAlign.Center,
                            color = white,
                            style = TextStyle(
                                fontFamily = inter,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                lineHeight = 18.sp
                            )
                        )
                    }
                }
            }

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
                    VerticalSpacer(20.dp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .background(gallery, MaterialTheme.shapes.medium)
                            .padding(8.dp)
                    ) {
                        TabCard(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            tab = Tab.DESCRIPTION,
                            selectedTab = selectedTab,
                            textResource = R.string.job_description_tab,
                            onTabClicked = { onTabClicked(Tab.DESCRIPTION) }
                        )
                        HorizontalSpacer(8.dp)
                        TabCard(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            tab = Tab.COMPANY,
                            selectedTab = selectedTab,
                            textResource = R.string.job_company_tab,
                            onTabClicked = { onTabClicked(Tab.COMPANY) }
                        )
                    }
                    VerticalSpacer(16.dp)
                    Text(
                        text = stringResource(R.string.job_description),
                        style = TextStyle(
                            fontFamily = inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            lineHeight = 15.sp
                        ),
                        color = mineShaft
                    )
                    VerticalSpacer(8.dp)
                    Text(
                        text = job.description,
                        style = TextStyle(
                            fontFamily = inter,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            lineHeight = 15.sp
                        ),
                        textAlign = TextAlign.Justify,
                        color = gray
                    )
                    VerticalSpacer(24.dp)
                    Text(
                        text = stringResource(R.string.job_requirements),
                        style = TextStyle(
                            fontFamily = inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            lineHeight = 15.sp
                        ),
                        color = mineShaft
                    )
                    VerticalSpacer(8.dp)

                    val style = TextStyle(
                        fontFamily = inter,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        lineHeight = 15.sp,
                        textIndent = TextIndent(restLine = 14.75.sp)
                    )
                    val text = buildAnnotatedString {
                        for (requirement in job.requirements) {
                            withStyle(style.toParagraphStyle()) {
                                append("$bullet\t\t$requirement")
                            }
                        }
                    }
                    Column(modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())) {
                        Text(
                            text = text,
                            style = style,
                            color = gray
                        )
                    }
                    VerticalSpacer(20.dp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                    ) {
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            shape = MaterialTheme.shapes.large,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = eastBay,
                                contentColor = white
                            ),
                            onClick = { onApplyNowButtonClicked(job.id) }
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                text = stringResource(R.string.job_apply_now),
                                style = TextStyle(
                                    fontFamily = inter,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp,
                                    lineHeight = 22.sp
                                )
                            )
                        }
                        HorizontalSpacer(8.dp)
                        Button(
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f),
                            shape = MaterialTheme.shapes.large,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = white,
                                contentColor = eastBay
                            ),
                            onClick = { onBookmarkJobButtonClicked(job.id) }
                        ) {
                            Icon(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                painter = painterResource(R.drawable.ic_bookmark_home),
                                contentDescription = null
                            )
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun TabCard(
    modifier: Modifier = Modifier,
    tab: Tab,
    selectedTab: Tab,
    @StringRes textResource: Int,
    onTabClicked: (Tab) -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = if (selectedTab == tab) white else Color.Transparent,
        contentColor = if (selectedTab == tab) eastBay else gray,
        elevation = if (selectedTab == tab) 4.dp else 0.dp,
        onClick = { onTabClicked(tab) }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(textResource),
                style = TextStyle(
                    fontFamily = inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    lineHeight = 16.sp
                )
            )
        }
    }
}