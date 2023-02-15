package uz.rounded.data.remote.dto.main.home.news

data class NewsDataDto(
    val _id: String,
    val category: String,
    val createdAt: String,
    val imgUrl: String,
    val name: String,
    val totalViews:Int
)
