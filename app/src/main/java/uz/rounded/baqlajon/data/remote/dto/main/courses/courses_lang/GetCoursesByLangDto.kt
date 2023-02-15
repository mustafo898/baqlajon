package uz.rounded.data.remote.dto.main.courses.courses_lang

import uz.rounded.data.remote.dto.main.courses.sections.LabelDto

data class GetCoursesByLangDto(
    val _id: String,
    val color: String,
    val discount: Int,
    val labels: List<LabelDto>,
    val name: String,
    val price: Int
)