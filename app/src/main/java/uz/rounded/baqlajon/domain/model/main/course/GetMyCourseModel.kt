package uz.rounded.baqlajon.domain.model.main.course

data class GetMyCourseModel(
    val _id: String,
    val completedCount: Int,
    val course: GetCourseModel,
    val courseId: String,
    val createdAt: String,
    val isSeen: Boolean,
    val status: String,
    val updatedAt: String,
    val userId: String
)