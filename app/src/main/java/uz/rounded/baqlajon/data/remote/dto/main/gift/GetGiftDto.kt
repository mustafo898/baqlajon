package uz.rounded.baqlajon.data.remote.dto.main.gift

data class GetGiftDto(
    val _id: String,
    val coin: Int,
    val createdAt: String,
    val image: String,
    val name: String,
    val status: String
)