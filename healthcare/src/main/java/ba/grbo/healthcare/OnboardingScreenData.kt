package ba.grbo.healthcare

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class OnboardingScreenData(
    val backgroundColor: Color,
    @DrawableRes val imageResource: Int,
    @StringRes val titleTextResource: Int,
    @StringRes val descriptionTextResource: Int
)
