package uz.rounded.baqlajon.data.remote.dto.main.course

data class GetMyCourseDto(
    val _id: String,
    val completedCount: Int,
    val course: GetCourseDto,
    val courseId: String,
    val createdAt: String,
    val isSeen: Boolean,
    val status: String,
    val updatedAt: String,
    val userId: String
)