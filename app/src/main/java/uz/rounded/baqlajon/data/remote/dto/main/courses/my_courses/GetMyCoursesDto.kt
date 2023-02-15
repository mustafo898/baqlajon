package uz.rounded.data.remote.dto.main.courses.my_courses

data class GetMyCoursesDto(
    val _id: String,
    val courseId: String,
    val type: String,
    val status: String,
    val teacher: String,
    val percentage: Int,
    val nextLesson: NextLessonDto?=null,
    val currentLesson: CurrentLessonDto?=null
)