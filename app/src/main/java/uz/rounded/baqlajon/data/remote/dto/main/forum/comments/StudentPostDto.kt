package uz.rounded.data.remote.dto.main.forum.comments

data class StudentPostDto(
    val _id: String,
    val firstName: String,
    val imgUrl: String = "",
    val lastName: String
)