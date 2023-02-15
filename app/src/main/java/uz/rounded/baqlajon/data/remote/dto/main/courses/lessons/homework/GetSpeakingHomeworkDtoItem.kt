package uz.rounded.data.remote.dto.main.courses.lessons.homework

data class GetSpeakingHomeworkDtoItem(
    val _id: String,
    val score: Int,
    val sentence: String,
    val type: String
)