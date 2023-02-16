package uz.rounded.baqlajon.domain.model.auth.update_number

data class UpdatePhoneNumberModel(
    val otp: String,
    val phoneNumber: String
)