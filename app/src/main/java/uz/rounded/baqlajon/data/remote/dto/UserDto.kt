package uz.rounded.baqlajon.data.remote.dto

data class UserDto(
    val _id: String,
    val createdAt: String,
    val firstName: String,
    val image: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val token: String
)