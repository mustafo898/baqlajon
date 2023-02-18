package uz.rounded.baqlajon.data.remote.dto.main

data class CoinDto(
    val _id: String,
    val allCoin: Int,
    val friendsPaymet: Int,
    val friendsRegister: Int,
    val myPayment: Int,
    val myRegister: Int
)