package uz.rounded.baqlajon.data.remote.dto.main.course

data class GetCourseDto(
    val _id: String,
    val authorId: String,
    val createdAt: String,
    val description: String,
    val image: String,
    val language: String,
    val rating: Double,
    val studentCount: Int,
    val time: Int,
    val title: String,
    val updatedAt: String,
    val videoCount: Int,
    val viewCount: Int
)