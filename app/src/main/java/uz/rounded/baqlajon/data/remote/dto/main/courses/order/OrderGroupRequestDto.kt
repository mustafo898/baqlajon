package uz.rounded.data.remote.dto.main.courses.order

data class OrderGroupRequestDto(
    val courseId: String,
    val groupId: String,
    val type: String
)