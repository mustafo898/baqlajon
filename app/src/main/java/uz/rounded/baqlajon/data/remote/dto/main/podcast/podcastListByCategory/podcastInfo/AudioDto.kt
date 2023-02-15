package uz.rounded.data.remote.dto.main.podcast.podcastListByCategory.podcastInfo

data class AudioDto(
    val _id: String,
    val audioUrl: String,
    val imgUrl: String,
    val name: String,
    val duration: Double
)