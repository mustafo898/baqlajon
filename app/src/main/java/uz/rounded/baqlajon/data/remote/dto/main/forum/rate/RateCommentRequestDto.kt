package uz.rounded.data.remote.dto.main.forum.rate

data class RateCommentRequestDto(
    val like: Boolean,
    val dislike: Boolean,
    val replyId: String,
    val postId: String,
    )