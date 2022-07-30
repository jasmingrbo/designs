package ba.grbo.healthcare

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import ba.grbo.healthcare.ui.theme.lavenderMagenta
import ba.grbo.healthcare.ui.theme.riptide
import ba.grbo.healthcare.ui.theme.royalBlue

data class OnboardingScreenData(
    val backgroundColor: Color,
    @DrawableRes val imageResource: Int,
    @StringRes val titleTextResource: Int,
    @StringRes val descriptionTextResource: Int
) {
    companion object {
        val dummyData = listOf(
            OnboardingScreenData(
                backgroundColor = royalBlue,
                imageResource = R.drawable.prescription,
                titleTextResource = R.string.onboarding_consultation_title,
                descriptionTextResource = R.string.onboarding_consultation_description
            ),
            OnboardingScreenData(
                backgroundColor = riptide,
                imageResource = R.drawable.note,
                titleTextResource = R.string.onboarding_ehr_title,
                descriptionTextResource = R.string.onboarding_ehr_description
            ),
            OnboardingScreenData(
                backgroundColor = lavenderMagenta,
                imageResource = R.drawable.pills,
                titleTextResource = R.string.onboarding_medications_title,
                descriptionTextResource = R.string.onboarding_medications_description
            )
        )
    }
}