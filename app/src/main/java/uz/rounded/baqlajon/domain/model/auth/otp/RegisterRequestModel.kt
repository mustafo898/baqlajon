package uz.rounded.baqlajon.domain.model.auth.otp

data class RegisterRequestModel(
    val firstName: String,
    val image: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String
)