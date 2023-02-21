package uz.rounded.baqlajon.domain.model.main

data class UserProfileModel(
    val id: String,
    val code: CodeModel,
    val coin: CoinModel,
    val createdAt: String,
    val firstName: String,
    val image: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String
)