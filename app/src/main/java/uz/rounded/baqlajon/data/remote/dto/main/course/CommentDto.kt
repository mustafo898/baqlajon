package uz.rounded.baqlajon.data.remote.dto.main.course

data class CommentDto(
    val courseId: String,
    val createdAt: String,
    val rating: Int,
    val text: String,
    val updatedAt: String
)