package uz.rounded.data.remote.dto.main.courses.lessons

data class UpdateSpeakingDto(
    val courseId: String,
    val lessonId: String,
    val speaking: Int,
    val type: String
)