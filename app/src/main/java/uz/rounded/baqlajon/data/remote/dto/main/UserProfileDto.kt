package uz.rounded.baqlajon.data.remote.dto.main

data class UserProfileDto(
    val _id: String,
    val code: CodeDto,
    val coin: CoinDto,
    val createdAt: String,
    val firstName: String,
    val image: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String
)