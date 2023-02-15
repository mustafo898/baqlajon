package uz.rounded.data.remote.dto.main.saved

data class StudentDto(
    val _id: String,
    val firstName: String,
    val lastName: String,
    var imgUrl: String,
    val languageLevel: String
)