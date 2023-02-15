package uz.rounded.data.remote.dto.main.home.news

data class NewsPaginationDto(
    val `data`: List<NewsDataDto>,
    val total: Int
)
