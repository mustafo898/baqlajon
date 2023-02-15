package uz.rounded.data.remote.dto.main.podcast.podcastListByCategory

data class PodcastDataDto(
    val _id: String,
    val category: String,
    val imgUrl: String,
    val name: String,
    val createdAt: String = ""
)