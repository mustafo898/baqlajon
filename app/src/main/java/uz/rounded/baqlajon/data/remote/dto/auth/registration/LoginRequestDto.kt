package uz.rounded.baqlajon.data.remote.dto.auth.registration

data class LoginRequestDto(
    val phoneNumber: String,
    val password: String
)