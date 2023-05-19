package uz.rounded.baqlajon.domain.model.main.course

data class VideoModel(
    val _id: String,
    val courseId: String,
    val createdAt: String,
    val description: String,
    val imageUrl: String,
    val index: Int,
    val isFree: Boolean,
    val title: String,
    val time: Int,
    val updatedAt: String,
    var videoUrl: String,
    val viewCount: Int,
    var fileUrl: List<String>? = emptyList()
)