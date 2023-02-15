package uz.rounded.data.remote.dto.main.courses.lessons

data class UpdateListeningDto(
    val courseId: String,
    val lessonId: String,
    val listening: Int,
    val type: String
)