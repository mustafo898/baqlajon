package uz.rounded.baqlajon.domain.model.auth.register

data class RegisterModel(
    val firstName: String,
    val image: String? = null,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val promocode: String
)