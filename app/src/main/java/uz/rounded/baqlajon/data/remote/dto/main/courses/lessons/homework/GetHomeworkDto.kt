package uz.rounded.data.remote.dto.main.courses.lessons.homework

data class GetHomeworkDto(
    val _id: String,
    val audioUrl: String? = null,
    val options: List<String>? = null,
    val score: Int? = null,
    val trueAnswer: String? = null,
    val type: String? = null,
    val sentence: String? = null,
)