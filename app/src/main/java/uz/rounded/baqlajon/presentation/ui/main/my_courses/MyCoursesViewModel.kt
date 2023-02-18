package uz.rounded.baqlajon.presentation.ui.main.my_courses

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
import uz.rounded.baqlajon.domain.model.main.course.GetMyCourseModel
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.common.UIListState
import javax.inject.Inject

@HiltViewModel
class MyCoursesViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    private val _allCourse = MutableStateFlow(UIListState<GetMyCourseModel>())
    val allCourse = _allCourse.asStateFlow()

    fun getAllMyCourse() {
        viewModelScope.launch {
            mainRepository.getAllMyCourse().onEach {
                when (it) {
                    is Resource.Error -> {
                        _allCourse.value = UIListState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _allCourse.value = UIListState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _allCourse.value = UIListState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

    private val _status = MutableStateFlow(UIListState<GetMyCourseModel>())
    val status = _status.asStateFlow()

    fun getStatus(status: String) {
        viewModelScope.launch {
            mainRepository.getMyCourseStatus(status).onEach {
                when (it) {
                    is Resource.Error -> {
                        _status.value = UIListState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _status.value = UIListState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _status.value = UIListState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }
}