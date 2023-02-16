package uz.rounded.baqlajon.data.remote.dto.auth.login

data class LoginRequestDto(
    val phoneNumber: String,
    val password: String
)