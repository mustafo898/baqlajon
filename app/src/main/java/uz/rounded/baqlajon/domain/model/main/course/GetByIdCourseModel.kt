package uz.rounded.baqlajon.domain.model.main.course

data class GetByIdCourseModel(
    val _id: String,
    val author: AuthorModel,
    val authorId: String,
    val comment: List<CommentModel>,
    val createdAt: String,
    val description: String,
    val freeVideo: List<VideoModel>,
    val image: String,
    val language: String,
    val rating: Double,
    val studentCount: Int,
    val time: Int,
    val title: String,
    val updatedAt: String,
    val video: List<VideoModel>,
    val videoCount: Int,
    val viewCount: Int
)