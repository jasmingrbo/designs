package ba.grbo.doctors

import androidx.annotation.DrawableRes

data class Doctor(
    val fullName: String,
    @DrawableRes val pictureResource: Int,
    val specialization: Specialization,
    val hospital: String,
    val available: Boolean,
    val rating: Rating,
) {
    data class Rating(val value: Double, val raters: Int)
}
