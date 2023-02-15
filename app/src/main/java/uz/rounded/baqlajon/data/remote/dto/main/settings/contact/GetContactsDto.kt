package uz.rounded.data.remote.dto.main.settings.contact

data class GetContactsDto(
    val _id: String,
    val email: String,
    val facebook: String,
    val instagram: String,
    val phoneNumber: String,
    val telegram: String,
    val tiktok: String,
    val youtube: String
)