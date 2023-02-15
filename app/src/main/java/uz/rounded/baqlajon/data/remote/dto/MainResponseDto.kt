package uz.rounded.baqlajon.data.remote.dto

data class MainResponseDto<T>(
    val code: Int,
    val data: T,
    val message: String,
    val statusCode: Int,
    val success: Boolean,
    val time: String
)