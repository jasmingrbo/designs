package ba.grbo.jobfinder

import androidx.annotation.DrawableRes
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import ba.grbo.jobfinder.EmploymentType.FULL
import ba.grbo.jobfinder.Seniority.JUNIOR
import ba.grbo.jobfinder.Seniority.SENIOR
import ba.grbo.jobfinder.ui.theme.dodgerBlue
import ba.grbo.jobfinder.ui.theme.white

data class Job(
    val id: Int,
    val title: String,
    val employer: String,
    val location: String,
    val hourlyWageRange: Pair<Int, Int>,
    val employmentType: EmploymentType,
    val remote: Boolean,
    val seniority: Seniority,
    val bookmarked: Boolean,
    val logoBackgroundColor: Color,
    @DrawableRes val employerLogoResource: Int,
)

enum class EmploymentType {
    FULL,
    PART
}

enum class Seniority {
    ENTRY,
    JUNIOR,
    MEDIOR,
    SENIOR
}

// Dummy data
val popularJobs = listOf(
    Job(
        id = 1,
        title = "Senior Project Manager",
        employer = "Tokopedia",
        location = "Jakarta",
        hourlyWageRange = 50 to 75,
        employmentType = FULL,
        remote = true,
        seniority = SENIOR,
        bookmarked = false,
        logoBackgroundColor = white,
        employerLogoResource = R.drawable.tokopedia
    ),
    Job(
        id = 2,
        title = "Junior Graphics Designer",
        employer = "OLX",
        location = "Jakarta",
        hourlyWageRange = 20 to 30,
        employmentType = FULL,
        remote = true,
        seniority = JUNIOR,
        bookmarked = false,
        logoBackgroundColor = dodgerBlue.copy(alpha = 0.1f),
        employerLogoResource = R.drawable.olx
    )
)
