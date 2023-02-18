package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail

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
import uz.rounded.baqlajon.domain.model.CommentModel
import uz.rounded.baqlajon.domain.model.RequestCommentModel
import uz.rounded.baqlajon.domain.model.VideoModel
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.common.UIObjectState
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _detail = MutableStateFlow(UIObjectState<VideoModel>())
    val detail = _detail.asStateFlow()

    fun getDetailVideo(id: String) {
        viewModelScope.launch {
            mainRepository.getByIdVideo(id).onEach {
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

    private val _finish = MutableStateFlow(UIObjectState<Boolean>())
    val finish = _finish.asStateFlow()

    fun finishVideo(id: String) {
        viewModelScope.launch {
            mainRepository.finishVideo(id).onEach {
                when (it) {
                    is Resource.Error -> {
                        _finish.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _finish.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _finish.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

    private val _create = MutableStateFlow(UIObjectState<CommentModel>())
    val create = _create.asStateFlow()

    fun createComment(id: String, requestCommentModel: RequestCommentModel) {
        viewModelScope.launch {
            mainRepository.createComment(id, requestCommentModel).onEach {
                when (it) {
                    is Resource.Error -> {
                        _create.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _create.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _create.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }
}