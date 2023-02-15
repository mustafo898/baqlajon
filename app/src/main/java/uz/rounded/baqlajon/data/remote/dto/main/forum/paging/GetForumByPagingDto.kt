package uz.rounded.data.remote.dto.main.forum.paging

data class GetForumByPagingDto(
    val data: List<DataPagingDto>,
    val total: Int
)