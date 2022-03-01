package ba.grbo.doctors

enum class Specialization(private val value: String) {
    HEART("Heart"),
    DENTAL("Dental"),
    EYE("Eye");

    override fun toString() = value
}