package uz.rounded.data.remote.dto.main.podcast.podcastListByCategory.podcastInfo

data class PodcastInfoDto(
    val _id: String,
    val audios: List<AudioDto>,
    val imgUrl: String,
    val name: String,
    val description:String,
    val isSaved:Boolean
)