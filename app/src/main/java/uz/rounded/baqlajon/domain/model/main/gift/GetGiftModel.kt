package uz.rounded.baqlajon.domain.model.main.gift

data class GetGiftModel(
    val _id: String,
    val coin: Int,
    val createdAt: String,
    val image: String,
    val name: String,
    val status: String
)