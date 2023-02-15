package uz.rounded.baqlajon.data.remote.dto.auth.otp

data class RegisterRequestDto(
    val firstName: String,
    val image: String,
    val lastName: String,
    val otp: String,
    val password: String,
    val phoneNumber: String
)