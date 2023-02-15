package uz.rounded.data.remote.dto.main.forum.paging

data class DataPagingDto(
    val _id: String,
    val category: CategoryPagingDto,
    val imgUrl: String,
    val replyCount: Int,
    val student: StudentPagingDto,
    val text: String,
    val totalLikes: Int,
    val isLiked: Boolean,
    val isSaved: Boolean,
    val viewCount: Int
)