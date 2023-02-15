package uz.rounded.data.remote.dto.main.courses.group

import uz.rounded.data.remote.dto.main.courses.sections.LabelDto

data class CourseDetailDto(
    val _id: String,
    val labels: List<LabelDto>,
    val name: String
)