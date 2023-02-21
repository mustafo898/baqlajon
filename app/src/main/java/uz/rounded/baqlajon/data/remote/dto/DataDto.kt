package uz.rounded.baqlajon.data.remote.dto

data class DataDto(
    val _id: String,
    val code: CodeDto? = null,
    val coin: CoinDto? = null,
    val createdAt: String,
    val firstName: String,
    val image: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String
)