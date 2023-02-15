package uz.rounded.data.remote.dto.main.courses.teacher

data class TeacherResponseDto(
    val _id: String,
    val firstName: String,
    val imgUrl: String,
    val introText: String,
    val languageLevel: String,
    val lastName: String,
    val rating: Double
)