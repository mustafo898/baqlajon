package uz.rounded.baqlajon.domain.model.auth.password

data class ForgotPasswordModel(
    val phoneNumber: String,
    val password: String,
)
