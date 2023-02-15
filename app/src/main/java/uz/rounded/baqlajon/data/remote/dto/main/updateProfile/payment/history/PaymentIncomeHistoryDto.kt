package uz.rounded.data.remote.dto.main.updateProfile.payment.history

data class PaymentIncomeHistoryDto(
    val _id: String,
    val createdAt: String,
    val paymentType: String,
    val status: String,
    val amount:Int
)