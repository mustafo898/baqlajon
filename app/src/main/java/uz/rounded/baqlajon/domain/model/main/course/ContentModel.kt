package uz.rounded.baqlajon.domain.model.main.course

data class ContentModel(
    val content: List<ContentItemModel>,
    val courseId: String,
    val isFree: Boolean
)