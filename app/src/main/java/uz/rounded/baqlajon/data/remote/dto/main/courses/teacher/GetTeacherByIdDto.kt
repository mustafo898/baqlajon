package uz.rounded.data.remote.dto.main.courses.teacher

data class GetTeacherByIdDto(
    val _id: String,
    val firstName: String,
    val imgUrl: String,
    val introText: String,
    val languageLevel: String,
    val lastName: String,
    val lessonPrice: Int,
    val motto: String,
    val rating: Double
)