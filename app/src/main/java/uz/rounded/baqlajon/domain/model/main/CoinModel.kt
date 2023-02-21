package uz.rounded.baqlajon.domain.model.main

data class CoinModel(
    val id: String,
    val allCoin: Int,
    val friendsPaymet: Int,
    val friendsRegister: Int,
    val myPayment: Int,
    val myRegister: Int
)