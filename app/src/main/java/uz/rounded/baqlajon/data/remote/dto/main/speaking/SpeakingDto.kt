package uz.rounded.data.remote.dto.main.speaking

data class SpeakingDto(
    val _id: String,
    val videoUrl: String,
    val title: String,
    val text: String,
    val score: Int
)