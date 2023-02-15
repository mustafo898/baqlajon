package uz.rounded.data.remote.dto.main.courses.group

data class TeacherDto(
    val _id: String,
    val firstName: String,
    val lastName: String,
    val imgUrl:String,
    val rating:Double,
    val motto:String?=null,
    val languageLevel:String
)