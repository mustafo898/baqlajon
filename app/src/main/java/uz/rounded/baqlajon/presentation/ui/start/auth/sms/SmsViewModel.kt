package uz.rounded.baqlajon.presentation.ui.start.auth.sms

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
import uz.rounded.baqlajon.domain.model.UserResponseModel
import uz.rounded.baqlajon.domain.model.auth.otp.CheckOtpModel
import uz.rounded.baqlajon.domain.model.auth.otp.SendOtpModel
import uz.rounded.baqlajon.domain.model.auth.register.RegisterModel
import uz.rounded.baqlajon.domain.repository.AuthRepository
import uz.rounded.baqlajon.presentation.common.UIObjectState
import javax.inject.Inject

@HiltViewModel
class SmsViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _registration = MutableStateFlow(UIObjectState<UserResponseModel>())
    val registration = _registration.asStateFlow()

    private val _create = MutableStateFlow(UIObjectState<String>())
    val create = _create.asStateFlow()

    private val _check = MutableStateFlow(UIObjectState<Boolean>())
    val check = _check.asStateFlow()

    private val _updatePhone = MutableStateFlow(UIObjectState<UserResponseModel>())
    val updatePhone = _updatePhone.asStateFlow()

    fun register(registrationModel: RegisterModel) {
        viewModelScope.launch {
            repository.register(registrationModel).onEach {
                when (it) {
                    is Resource.Error -> {
                        _registration.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _registration.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _registration.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

    fun createOtp(otpModel: SendOtpModel) {
        viewModelScope.launch {
            repository.createOtp(otpModel).onEach {
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

    fun createForgetOtp(otpModel: SendOtpModel) {
        viewModelScope.launch {
            repository.createForgetOtp(otpModel).onEach {
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

    fun checkOtp(otpModel: CheckOtpModel) {
        viewModelScope.launch {
            repository.checkOtp(otpModel).onEach {
                when (it) {
                    is Resource.Error -> {
                        _check.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _check.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _check.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

    fun updatePhone(otpModel: CheckOtpModel) {
        viewModelScope.launch {
            repository.updatePhone(otpModel).onEach {
                when (it) {
                    is Resource.Error -> {
                        _updatePhone.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _updatePhone.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _updatePhone.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }
}