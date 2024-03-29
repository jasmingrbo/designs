package ba.grbo.jobfinder

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import ba.grbo.jobfinder.EmploymentType.FULL
import ba.grbo.jobfinder.EmploymentType.PART
import ba.grbo.jobfinder.Seniority.JUNIOR
import ba.grbo.jobfinder.Seniority.MEDIOR
import ba.grbo.jobfinder.Seniority.SENIOR
import ba.grbo.jobfinder.ui.theme.amaranth
import ba.grbo.jobfinder.ui.theme.black
import ba.grbo.jobfinder.ui.theme.cerulean
import ba.grbo.jobfinder.ui.theme.chateauGreen
import ba.grbo.jobfinder.ui.theme.dodgerBlue
import ba.grbo.jobfinder.ui.theme.funGreen
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
    @DrawableRes val jobImageResource: Int = R.drawable.job,
    val description: String,
    val requirements: List<String>
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
        employerLogoResource = R.drawable.tokopedia,
        description = "Project managers play the lead role in planning, executing, monitoring, controlling, and closing out projects. They are accountable for the entire project scope, the project team and resources, the project budget, and the success or failure of the project.",
        requirements = listOf(
            "Bachelor's degree in computer science, business, or a related field",
            "5-8 years of project management and related experience",
            "Project Management Professional (PMP) certification preferred",
            "Proven ability to solve problems creatively",
            "Strong familiarity with project management software tools, methodologies, and best practices"
        )
    ),
    Job(
        id = 2,
        title = "Junior Graphic Designer",
        employer = "OLX",
        location = "Jakarta",
        hourlyWageRange = 20 to 30,
        employmentType = FULL,
        remote = true,
        seniority = JUNIOR,
        bookmarked = false,
        logoBackgroundColor = dodgerBlue.copy(alpha = 0.1f),
        employerLogoResource = R.drawable.olx,
        description = "Project managers play the lead role in planning, executing, monitoring, controlling, and closing out projects. They are accountable for the entire project scope, the project team and resources, the project budget, and the success or failure of the project.",
        requirements = listOf(
            "Bachelor's degree in computer science, business, or a related field",
            "5-8 years of project management and related experience",
            "Project Management Professional (PMP) certification preferred",
            "Proven ability to solve problems creatively",
            "Strong familiarity with project management software tools, methodologies, and best practices"
        )
    )
)

val jobs = listOf(
    Job(
        id = 3,
        title = "FE Developer",
        employer = "Google",
        location = "Jakarta",
        hourlyWageRange = 60 to 80,
        employmentType = FULL,
        remote = true,
        seniority = SENIOR,
        bookmarked = false,
        logoBackgroundColor = chateauGreen.copy(alpha = 0.1f),
        employerLogoResource = R.drawable.google,
        description = "Project managers play the lead role in planning, executing, monitoring, controlling, and closing out projects. They are accountable for the entire project scope, the project team and resources, the project budget, and the success or failure of the project.",
        requirements = listOf(
            "Bachelor's degree in computer science, business, or a related field",
            "5-8 years of project management and related experience",
            "Project Management Professional (PMP) certification preferred",
            "Proven ability to solve problems creatively",
            "Strong familiarity with project management software tools, methodologies, and best practices"
        )
    ),
    Job(
        id = 4,
        title = "Finance",
        employer = "Facebook",
        location = "Jakarta",
        hourlyWageRange = 40 to 70,
        employmentType = FULL,
        remote = true,
        seniority = MEDIOR,
        bookmarked = false,
        logoBackgroundColor = dodgerBlue.copy(alpha = 0.1f),
        employerLogoResource = R.drawable.facebook,
        description = "Project managers play the lead role in planning, executing, monitoring, controlling, and closing out projects. They are accountable for the entire project scope, the project team and resources, the project budget, and the success or failure of the project.",
        requirements = listOf(
            "Bachelor's degree in computer science, business, or a related field",
            "5-8 years of project management and related experience",
            "Project Management Professional (PMP) certification preferred",
            "Proven ability to solve problems creatively",
            "Strong familiarity with project management software tools, methodologies, and best practices"
        )
    ),
    Job(
        id = 5,
        title = "Graphic Designer",
        employer = "Bukalapak",
        location = "Jakarta",
        hourlyWageRange = 60 to 80,
        employmentType = FULL,
        remote = true,
        seniority = SENIOR,
        bookmarked = false,
        logoBackgroundColor = amaranth.copy(alpha = 0.1f),
        employerLogoResource = R.drawable.bukalapak,
        description = "Project managers play the lead role in planning, executing, monitoring, controlling, and closing out projects. They are accountable for the entire project scope, the project team and resources, the project budget, and the success or failure of the project.",
        requirements = listOf(
            "Bachelor's degree in computer science, business, or a related field",
            "5-8 years of project management and related experience",
            "Project Management Professional (PMP) certification preferred",
            "Proven ability to solve problems creatively",
            "Strong familiarity with project management software tools, methodologies, and best practices"
        )
    ),
    Job(
        id = 6,
        title = "UX Writer",
        employer = "Gojek",
        location = "Jakarta",
        hourlyWageRange = 30 to 50,
        employmentType = PART,
        remote = true,
        seniority = SENIOR,
        bookmarked = false,
        logoBackgroundColor = funGreen.copy(alpha = 0.1f),
        employerLogoResource = R.drawable.gojek,
        description = "Project managers play the lead role in planning, executing, monitoring, controlling, and closing out projects. They are accountable for the entire project scope, the project team and resources, the project budget, and the success or failure of the project.",
        requirements = listOf(
            "Bachelor's degree in computer science, business, or a related field",
            "5-8 years of project management and related experience",
            "Project Management Professional (PMP) certification preferred",
            "Proven ability to solve problems creatively",
            "Strong familiarity with project management software tools, methodologies, and best practices"
        )
    ),
    Job(
        id = 7,
        title = "Swift Dev",
        employer = "Apple",
        location = "Jakarta",
        hourlyWageRange = 70 to 100,
        employmentType = PART,
        remote = true,
        seniority = SENIOR,
        bookmarked = false,
        logoBackgroundColor = black.copy(alpha = 0.1f),
        employerLogoResource = R.drawable.apple,
        description = "Project managers play the lead role in planning, executing, monitoring, controlling, and closing out projects. They are accountable for the entire project scope, the project team and resources, the project budget, and the success or failure of the project.",
        requirements = listOf(
            "Bachelor's degree in computer science, business, or a related field",
            "5-8 years of project management and related experience",
            "Project Management Professional (PMP) certification preferred",
            "Proven ability to solve problems creatively",
            "Strong familiarity with project management software tools, methodologies, and best practices"
        )
    ),
    Job(
        id = 8,
        title = "FE Dev",
        employer = "Twitter",
        location = "Jakarta",
        hourlyWageRange = 40 to 60,
        employmentType = PART,
        remote = true,
        seniority = MEDIOR,
        bookmarked = false,
        logoBackgroundColor = cerulean.copy(alpha = 0.1f),
        employerLogoResource = R.drawable.twitter,
        description = "Project managers play the lead role in planning, executing, monitoring, controlling, and closing out projects. They are accountable for the entire project scope, the project team and resources, the project budget, and the success or failure of the project.",
        requirements = listOf(
            "Bachelor's degree in computer science, business, or a related field",
            "5-8 years of project management and related experience",
            "Project Management Professional (PMP) certification preferred",
            "Proven ability to solve problems creatively",
            "Strong familiarity with project management software tools, methodologies, and best practices"
        )
    ),
)
