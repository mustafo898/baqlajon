package uz.rounded.baqlajon.domain.model.main.profile

data class UpdateUserRequestModel(
    val firstName: String,
    val image: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String
)