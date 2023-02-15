package uz.rounded.baqlajon.presentation.ui.start.auth.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.rounded.baqlajon.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: AuthRepository
) : ViewModel() {

//    private val _login = MutableStateFlow(UIObjectState<UserProfile>())
//    val login = _login.asStateFlow()
//
//    fun login(registrationModel: LoginModel) {
//        viewModelScope.launch {
//            useCase.login(registrationModel).onEach {
//                when (it) {
//                    is Resource.Error -> {
//                        _login.value = UIObjectState(it.message ?: "")
//                    }
//                    is Resource.Loading -> {
//                        _login.value = UIObjectState(isLoading = true)
//                    }
//                    is Resource.Success -> {
//                        _login.value = UIObjectState(data = it.data)
//                    }
//                }
//            }.launchIn(CoroutineScope(Dispatchers.IO))
//        }
//    }
//
//
//    private val _device = MutableStateFlow(UIObjectState<String>())
//    val device = _device.asStateFlow()
//
//    fun getDevice(deviceModel: DeviceModel) {
//        viewModelScope.launch {
//            mainUseCase.device(deviceModel).onEach {
//                when (it) {
//                    is Resource.Error -> {
//                        _device.value = UIObjectState(it.message ?: "")
//                    }
//                    is Resource.Loading -> {
//                        _device.value = UIObjectState(isLoading = true)
//                    }
//                    is Resource.Success -> {
//                        _device.value = UIObjectState(data = it.data)
//                    }
//                }
//            }.launchIn(CoroutineScope(Dispatchers.IO))
//        }
//    }
//

}