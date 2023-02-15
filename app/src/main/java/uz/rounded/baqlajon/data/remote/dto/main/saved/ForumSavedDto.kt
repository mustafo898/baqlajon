package uz.rounded.data.remote.dto.main.saved

data class ForumSavedDto(
    val _id: String,
    val imgUrl: String,
    val text: String,
    val totalLikes: Int,
    val replyCount: Int,
    val viewCount: Int
)