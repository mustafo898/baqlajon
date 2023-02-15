package uz.rounded.data.remote.dto.main.courses.lessons

data class UpdateHomeWorkDto(
    val courseId: String,
    val lessonId: String,
    val homework: Int,
    val type: String
)