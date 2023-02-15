package uz.rounded.data.remote.dto.main.courses.sections

data class SectionsDto(
    val _id: String,
    val imgUrl: String,
    val name: String,
    val color: String,
    val labels: List<LabelDto>,
    val createdAt: String,
    val description:String,
    val status:String
)