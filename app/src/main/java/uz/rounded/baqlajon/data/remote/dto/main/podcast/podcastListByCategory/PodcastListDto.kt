package uz.rounded.data.remote.dto.main.podcast.podcastListByCategory

data class PodcastListDto(
    val `data`: List<PodcastDataDto>,
    val total: Int
)