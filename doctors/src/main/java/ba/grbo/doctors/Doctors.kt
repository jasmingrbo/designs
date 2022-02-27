package ba.grbo.doctors

object Doctors {
    val value = listOf(
        Doctor(
            id = 1,
            fullName = "Gilang Segara Bening",
            pictureResource = R.drawable.gilang_segara_bening,
            specialization = Specialization.HEART,
            hospital = "Persahabatan",
            experience = 3,
            patients = 1221,
            description = "dr. Gilang is one of the best doctors in the Persahabatan Hospital. He has saved more than 1000 patients in the past 3 years. He has also received many awards from domestic and abroad as the best doctors. He is available on a private or schedule. ",
            available = true,
            rating = 5.0
        ),
        Doctor(
            id = 2,
            fullName = "Shabil Chan",
            pictureResource = R.drawable.shabil_chan,
            specialization = Specialization.DENTAL,
            hospital = "Columbia Asia",
            experience = 3,
            patients = 964,
            description = "dr. Shabil is one of the best doctors in the Columbia Asia Hospital. She has saved more than 1000 patients in the past 3 years. He has also received many awards from domestic and abroad as the best doctors. He is available on a private or schedule. ",
            available = true,
            rating = 5.0
        ),
        Doctor(
            id = 3,
            fullName = "Mustakim",
            pictureResource = R.drawable.mustakim,
            specialization = Specialization.EYE,
            hospital = "Salemba Carolus",
            experience = 22,
            patients = 762,
            description = "dr. Mustakim is one of the best doctors in the Salemba Carolus Hospital. He has saved more than 1000 patients in the past 3 years. He has also received many awards from domestic and abroad as the best doctors. He is available on a private or schedule. ",
            available = false,
            rating = 5.0
        ),
        Doctor(
            id = 4,
            fullName = "Suprihatini",
            pictureResource = R.drawable.suprihatini,
            specialization = Specialization.HEART,
            hospital = "Persahabatan",
            experience = 10,
            patients = 943,
            description = "dr. Suprihatini is one of the best doctors in the Persahabatan Hospital. She has saved more than 1000 patients in the past 3 years. He has also received many awards from domestic and abroad as the best doctors. He is available on a private or schedule. ",
            available = true,
            rating = 5.0
        )
    )
}