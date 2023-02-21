package uz.rounded.baqlajon.domain.model

data class DataModel(
    val _id: String,
    val code: CodeModel?=null,
    val coin: CoinModel?=null,
    val createdAt: String,
    val firstName: String,
    var image: String,
    val lastName: String,
    var password: String,
    var phoneNumber: String
)