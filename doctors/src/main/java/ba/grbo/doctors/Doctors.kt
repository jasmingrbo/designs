package ba.grbo.doctors

object Doctors {
    val value = listOf(
        Doctor(
            id = 1,
            fullName = "Gilang Segara Bening",
            pictureResource = R.drawable.gilang_segara_bening,
            specialization = Specialization.HEART,
            hospital = "Persahabatan",
            available = true,
            rating = Doctor.Rating(5.0, 1221)
        ),
        Doctor(
            id = 2,
            fullName = "Shabil Chan",
            pictureResource = R.drawable.shabil_chan,
            specialization = Specialization.DENTAL,
            hospital = "Columbia Asia",
            available = true,
            rating = Doctor.Rating(5.0, 964)
        ),
        Doctor(
            id = 3,
            fullName = "Mustakim",
            pictureResource = R.drawable.mustakim,
            specialization = Specialization.EYE,
            hospital = "Salemba Carolus",
            available = false,
            rating = Doctor.Rating(5.0, 762)
        ),
        Doctor(
            id = 4,
            fullName = "Suprihatini",
            pictureResource = R.drawable.suprihatini,
            specialization = Specialization.HEART,
            hospital = "Persahabatan",
            available = true,
            rating = Doctor.Rating(5.0, 943)
        )
    )
}