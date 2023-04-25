package uz.rounded.baqlajon.presentation.ui.main.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.rounded.baqlajon.domain.model.DataModel
import uz.rounded.baqlajon.domain.model.main.course.GetCourseModel
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.common.UIListState
import uz.rounded.baqlajon.presentation.common.UIObjectState
import uz.rounded.baqlajon.presentation.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {

    private val _allCourse = MutableStateFlow(UIListState<GetCourseModel>())
    val allCourse = _allCourse.asStateFlow()

    fun getAllCourse() {
        getDataList({ mainRepository.getAllCourse() }, _allCourse)
    }

    private val _user = MutableStateFlow(UIObjectState<DataModel>())
    val user = _user.asStateFlow()

    fun getProfile() {
        getDataObject({ mainRepository.getProfile() }, _user)
    }

    private val _popular = MutableStateFlow(UIListState<GetCourseModel>())
    val popular = _popular.asStateFlow()

    fun getPopular() {
        getDataList({ mainRepository.getPopularCourse() }, _popular)
    }

    private val _newest = MutableStateFlow(UIListState<GetCourseModel>())
    val newest = _newest.asStateFlow()

    fun getNewest() {
        getDataList({ mainRepository.getNewestCourse() }, _newest)
    }

}