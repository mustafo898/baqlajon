package uz.rounded.data.remote.dto.main.courses.lessons

data class GetMembersDto(
    val _id: String,
    val name: String,
    val lastName: String,
    val imgUrl: String,
    val lastOnline: Long
)