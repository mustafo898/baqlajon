package uz.rounded.baqlajon.domain.model.main.course

data class CommentModel(
    val courseId: String,
    val createdAt: String,
    val rating: Int,
    val text: String,
    val updatedAt: String
)