package uz.rounded.data.remote.dto.main.courses.lessons.homework

data class GetTranslatingHomeworkDto(
    val _id: String,
    val options: List<String>,
    val score: Int,
    val sentence: String,
    val trueAnswer: String,
    val type: String
)