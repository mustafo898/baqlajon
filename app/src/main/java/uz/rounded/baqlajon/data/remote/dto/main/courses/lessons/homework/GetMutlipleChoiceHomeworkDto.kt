package uz.rounded.data.remote.dto.main.courses.lessons.homework

data class GetMutlipleChoiceHomeworkDto(
    val _id: String,
    val options: List<String>,
    val score: Int,
    val sentence: String,
    val type: String
)