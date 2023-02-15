package uz.rounded.data.remote.dto.main.courses.lessons


data class GetLessonsByIdDto(
    val _id: Boolean,
    val lessons: List<LessonsByIdDto>
)