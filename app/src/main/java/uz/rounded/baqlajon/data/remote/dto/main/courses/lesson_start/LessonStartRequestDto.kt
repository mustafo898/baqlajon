package uz.rounded.data.remote.dto.main.courses.lesson_start

data class LessonStartRequestDto(
    val courseId: String,
    val lessonId: String,
    val type: String
)