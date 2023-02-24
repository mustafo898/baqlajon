package uz.rounded.baqlajon.presentation.ui.main.my_courses

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.rounded.baqlajon.domain.model.main.course.GetMyCourseModel
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.common.UIListState
import uz.rounded.baqlajon.presentation.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MyCoursesViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {
    private val _allCourse = MutableStateFlow(UIListState<GetMyCourseModel>())
    val allCourse = _allCourse.asStateFlow()

    fun getAllMyCourse() {
        getDataList({ mainRepository.getAllMyCourse() }, _allCourse)
    }

    private val _status = MutableStateFlow(UIListState<GetMyCourseModel>())
    val status = _status.asStateFlow()

    fun getStatus(status: String) {
        getDataList({ mainRepository.getMyCourseStatus(status = status) }, _status)
    }
}