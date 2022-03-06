package ba.grbo.jobfinder

enum class JobCategory(val value: String) {
    ALL("All Jobs"),
    UI_DESIGNER("UI Designer"),
    FE_DEV("FE Dev"),
    PM("PM"),
    BE_DEV("BE Dev"),
    ANDROID_DEV("Android Dev"),
    JAVA_DEV("Java Dev");

    companion object {
        fun fromValue(value: String) = values().find { category ->
            category.value == value
        } ?: throw IllegalArgumentException("Unknown value: $value")
    }
}