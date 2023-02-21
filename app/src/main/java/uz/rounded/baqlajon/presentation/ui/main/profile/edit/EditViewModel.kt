package uz.rounded.baqlajon.presentation.ui.main.profile.edit

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
import okhttp3.MultipartBody
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.domain.model.DataModel
import uz.rounded.baqlajon.domain.model.main.profile.UpdateUserRequestModel
import uz.rounded.baqlajon.domain.repository.AuthRepository
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.common.UIObjectState
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _user = MutableStateFlow(UIObjectState<DataModel>())
    val user = _user.asStateFlow()

    fun getUser() {
        viewModelScope.launch {
            mainRepository.getProfile().onEach {
                when (it) {
                    is Resource.Error -> {
                        _user.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _user.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _user.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

    private val _updateUser = MutableStateFlow(UIObjectState<DataModel>())
    val updateUser = _updateUser.asStateFlow()

    fun updateUser(updateUserRequestModel: UpdateUserRequestModel) {
        viewModelScope.launch {
            mainRepository.updateUser(updateUserRequestModel).onEach {
                when (it) {
                    is Resource.Error -> {
                        _updateUser.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _updateUser.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _updateUser.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

    private val _uploadImage = MutableStateFlow(UIObjectState<String>())
    val uploadImage = _uploadImage.asStateFlow()

    fun uploadImage(file: MultipartBody.Part) {
        viewModelScope.launch {
            mainRepository.uploadImage(file).onEach {
                when (it) {
                    is Resource.Error -> {
                        _uploadImage.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _uploadImage.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _uploadImage.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }
}