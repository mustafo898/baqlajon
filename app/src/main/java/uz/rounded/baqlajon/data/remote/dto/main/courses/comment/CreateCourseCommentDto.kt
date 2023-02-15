package uz.rounded.data.remote.dto.main.courses.comment

data class CreateCourseCommentDto(
    val courseId: String,
    val comment: String,
    val createdDate:Long
)