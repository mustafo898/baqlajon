package uz.rounded.data.remote.dto.main.updateProfile

data class UpdateProfile(
    val imgUrl: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val birthDate: String,
    val address: String,
    val password: String,
//    val email: String,
    val gender: String
)