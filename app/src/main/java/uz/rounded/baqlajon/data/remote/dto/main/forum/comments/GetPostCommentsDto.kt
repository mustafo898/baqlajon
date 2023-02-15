package uz.rounded.data.remote.dto.main.forum.comments

data class GetPostCommentsDto(
    val _id: String,
    val comment: String,
    val likeCount: Int,
    val student: StudentPostDto,
    val createdAt: String,
    val like: Boolean,
    val isSelected: Boolean,
    val dislike: Boolean,
    val isSaved:Boolean
)