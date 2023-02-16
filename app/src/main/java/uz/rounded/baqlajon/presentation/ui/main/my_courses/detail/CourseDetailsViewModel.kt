package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.domain.model.GetByIdCourseModel
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.common.UIObjectState
import javax.inject.Inject

@HiltViewModel
class CourseDetailsViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _detail = MutableStateFlow(UIObjectState<GetByIdCourseModel>())
    val detail = _detail.asStateFlow()

    fun getDetail(id: String) {
        viewModelScope.launch {
            repository.getByIdCourse(id).onEach {
                when (it) {
                    is Resource.Error -> {
                        _detail.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _detail.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _detail.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

}