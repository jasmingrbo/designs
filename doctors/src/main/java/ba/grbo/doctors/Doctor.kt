package ba.grbo.doctors

import androidx.annotation.DrawableRes

data class Doctor(
    val id: Int,
    val fullName: String,
    @DrawableRes val pictureResource: Int,
    val specialization: Specialization,
    val hospital: String,
    val experience: Int,
    val patients: Int,
    val description: String,
    val available: Boolean,
    val rating: Double,
)
