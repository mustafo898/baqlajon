package uz.rounded.baqlajon.data.remote.dto.main.profile

data class UpdateUserRequestDto(
    val firstName: String,
    val image: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String
)