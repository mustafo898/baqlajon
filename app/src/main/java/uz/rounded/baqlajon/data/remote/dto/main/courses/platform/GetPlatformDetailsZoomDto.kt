package uz.rounded.data.remote.dto.main.courses.platform

import uz.rounded.data.remote.dto.main.courses.sections.LabelDto

data class GetPlatformDetailsZoomDto(
    val _id: String,
    val color: String,
    val introImage: String,
    val introText: String,
    val introVideo: String,
    val labels: List<LabelDto>,
    val name: String,
    val price: Int,
    val discount:Int?= null,
    val commentCount:Int
)