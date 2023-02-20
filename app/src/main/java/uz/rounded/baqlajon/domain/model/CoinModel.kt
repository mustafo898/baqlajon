package uz.rounded.baqlajon.domain.model

data class CoinModel(
    val _id: String,
    val allCoin: Int,
    val friendsPaymet: Int,
    val friendsRegister: Int,
    val myPayment: Int,
    val myRegister: Int
)