package uz.rounded.data.remote.dto.main.podcast

data class PodcastCategoryDtoItem(
    val _id: String? = "",
    val category: CategoryDto = CategoryDto("",""),
    val podcasts: List<PodcastDto> = emptyList()
)