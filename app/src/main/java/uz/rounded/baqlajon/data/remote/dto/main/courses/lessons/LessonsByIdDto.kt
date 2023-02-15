package uz.rounded.data.remote.dto.main.courses.lessons

data class LessonsByIdDto(
    val _id: String,
    val isFinished: Boolean,
    val lesson: LessonDto,
    val isOpened: Boolean,
)