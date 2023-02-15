package uz.rounded.baqlajon.data.remote.dto

data class PagingMainDto<T>(
    val total: Int,
    val data: T
)
