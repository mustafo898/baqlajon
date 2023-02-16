package uz.rounded.baqlajon.domain.model

data class GetCourseModel(
    val id: String,
    val authorId: String,
    val createdAt: String,
    val description: String,
    val image: String,
    val language: String,
    val rating: Int,
    val studentCount: Int,
    val time: Int,
    val title: String,
    val updatedAt: String,
    val videoCount: Int,
    val viewCount: Int
)