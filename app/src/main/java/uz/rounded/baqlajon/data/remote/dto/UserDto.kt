package uz.rounded.baqlajon.data.remote.dto

data class UserDto(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val firstName: String,
    val image: String,
    val isDeleted: Boolean,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val updatedAt: String,
    val token: String
)