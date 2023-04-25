package uz.rounded.baqlajon.presentation.ui.main.home.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import uz.rounded.baqlajon.domain.model.main.course.GetCourseModel
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {

    suspend fun searchAllCourse(search: String): Flow<PagingData<GetCourseModel>> {
        return mainRepository.searchAllCourse(search).cachedIn(viewModelScope)
    }
}