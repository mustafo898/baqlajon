package uz.rounded.data.remote.dto.main.forum.reply

data class ReplyToPostRequestDto(
    val comment: String,
    val postId: String,
    val createdDate:Long
)