package uz.rounded.data.remote.dto.main.courses.comment

data class GetCourseCommentDto(
    val _id: String,
    val comment: String,
    val student: StudentDto,
    val createdDate:Long
)