package uz.rounded.baqlajon.data.remote.dto.main.course

data class ContentDto(
    val content: List<ContentItemDto>,
    val courseId: String,
    val isFree: Boolean
)