package uz.rounded.baqlajon.domain.model

data class UserModel(
    val _id: String? = "",
    val createdAt: String?="",
    val firstName: String,
    val image: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val token: String
)