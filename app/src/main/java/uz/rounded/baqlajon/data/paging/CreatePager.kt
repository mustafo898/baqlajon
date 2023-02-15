package uz.rounded.baqlajon.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig

fun <T : Any> createPager(
    pageSize: Int = 10, enablePlaceHolders: Boolean = false,
    block: suspend (Int) -> List<T>
): Pager<Int, T> = Pager(
    config = PagingConfig(
        enablePlaceholders = enablePlaceHolders,
        pageSize = 10,
    ),
    pagingSourceFactory = { BasePagingSource(block) }
)