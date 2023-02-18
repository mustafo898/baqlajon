package uz.rounded.baqlajon.presentation.ui.main.home

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
import uz.rounded.baqlajon.domain.model.main.UserProfileModel
import uz.rounded.baqlajon.domain.model.main.course.GetCourseModel
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.common.UIListState
import uz.rounded.baqlajon.presentation.common.UIObjectState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _allCourse = MutableStateFlow(UIListState<GetCourseModel>())
    val allCourse = _allCourse.asStateFlow()

    fun getAllCourse() {
        viewModelScope.launch {
            mainRepository.getAllCourse().onEach {
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

//    private val _user = MutableStateFlow(UIObjectState<UserProfileModel>())
//    val user = _user.asStateFlow()
//
//    fun getProfile() {
//        viewModelScope.launch {
//            mainRepository.getProfile().onEach {
//                when (it) {
//                    is Resource.Error -> {
//                        _user.value = UIObjectState(it.message ?: "")
//                    }
//                    is Resource.Loading -> {
//                        _user.value = UIObjectState(isLoading = true)
//                    }
//                    is Resource.Success -> {
//                        _user.value = UIObjectState(data = it.data)
//                    }
//                }
//            }.launchIn(CoroutineScope(Dispatchers.IO))
//        }
//    }

    private val _popular = MutableStateFlow(UIListState<GetCourseModel>())
    val popular = _popular.asStateFlow()

    fun getPopular() {
        viewModelScope.launch {
            mainRepository.getPopularCourse().onEach {
                when (it) {
                    is Resource.Error -> {
                        _popular.value = UIListState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _popular.value = UIListState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _popular.value = UIListState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

    private val _newest = MutableStateFlow(UIListState<GetCourseModel>())
    val newest = _newest.asStateFlow()

    fun getNewest() {
        viewModelScope.launch {
            mainRepository.getNewestCourse().onEach {
                when (it) {
                    is Resource.Error -> {
                        _newest.value = UIListState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _newest.value = UIListState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _newest.value = UIListState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }
}