package uz.rounded.data.remote.dto.main.courses.lessons.homework

data class GetListeningHomeworkDto(
    val _id: String,
    val audioUrl: String,
    val options: List<String>,
    val score: Int,
    val trueAnswer: String,
    val type: String
)