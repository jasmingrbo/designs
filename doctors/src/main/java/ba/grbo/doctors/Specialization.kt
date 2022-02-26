package ba.grbo.doctors

import java.util.Locale

enum class Specialization {
    HEART,
    DENTAL,
    EYE;

    override fun toString(): String {
        return name
            .lowercase()
            .replaceFirstChar { firstChar -> firstChar.titlecase(Locale.getDefault()) }
    }
}