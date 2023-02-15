package uz.rounded.baqlajon.data.remote.dto.main.course

data class GetByIdCourseDto(
    val _id: String,
    val author: AuthorDto,
    val authorId: String,
    val comment: List<CommentDto>,
    val createdAt: String,
    val description: String,
    val freeVideo: List<FreeVideoDto>,
    val image: String,
    val language: String,
    val rating: Int,
    val studentCount: Int,
    val time: Int,
    val title: String,
    val updatedAt: String,
    val video: List<VideoDto>,
    val videoCount: Int,
    val viewCount: Int
)