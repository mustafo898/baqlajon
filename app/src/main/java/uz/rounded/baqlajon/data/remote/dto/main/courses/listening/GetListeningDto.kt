package uz.rounded.data.remote.dto.main.courses.listening

data class GetListeningDto(
    val _id: String,
    val audioUrl: String,
    val score: Int,
    val text: String,
    val title: String
)