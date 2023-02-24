package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.rounded.baqlajon.domain.model.main.course.GetByIdCourseModel
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.common.UIObjectState
import uz.rounded.baqlajon.presentation.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CourseDetailsViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    private val _detail = MutableStateFlow(UIObjectState<GetByIdCourseModel>())
    val detail = _detail.asStateFlow()

    fun getDetail(id: String) {
        getDataObject({ repository.getByIdCourse(id = id) }, _detail)
    }

    private val _start = MutableStateFlow(UIObjectState<Boolean>())
    val start = _start.asStateFlow()

    fun startLesson(id: String) {
        getDataObject({ repository.getStartCourse(id = id) }, _start)
    }
}