package uz.rounded.data.remote.dto.main.settings.device

data class GetDevicesDto(
    val _id: String,
    val device: String,
    val ip_address: String,
    val studentId: String
)