package uz.rounded.data.remote.dto.main.news

import uz.rounded.data.remote.dto.main.ContentDto

data class NewsDto(
    val _id: String,
    val name: String,
    val imgUrl: String,
    val slug: String,
    val content: List<ContentDto>,
    val totalViews: Int,
)