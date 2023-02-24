package uz.rounded.baqlajon.presentation.ui.start.auth.sms

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.rounded.baqlajon.domain.model.UserResponseModel
import uz.rounded.baqlajon.domain.model.auth.otp.CheckOtpModel
import uz.rounded.baqlajon.domain.model.auth.otp.SendOtpModel
import uz.rounded.baqlajon.domain.model.auth.register.RegisterModel
import uz.rounded.baqlajon.domain.repository.AuthRepository
import uz.rounded.baqlajon.presentation.common.UIObjectState
import uz.rounded.baqlajon.presentation.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SmsViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel() {

    private val _registration = MutableStateFlow(UIObjectState<UserResponseModel>())
    val registration = _registration.asStateFlow()

    private val _create = MutableStateFlow(UIObjectState<String>())
    val create = _create.asStateFlow()

    private val _check = MutableStateFlow(UIObjectState<Boolean>())
    val check = _check.asStateFlow()

    private val _updatePhone = MutableStateFlow(UIObjectState<UserResponseModel>())
    val updatePhone = _updatePhone.asStateFlow()

    fun register(registrationModel: RegisterModel) {
        getDataObject({ repository.register(registrationModel) }, _registration)
    }

    fun createOtp(otpModel: SendOtpModel) {
        getDataObject({ repository.createOtp(otpModel) }, _create)
    }

    fun createForgetOtp(otpModel: SendOtpModel) {
        getDataObject({ repository.createForgetOtp(otpModel) }, _create)
    }

    fun checkOtp(otpModel: CheckOtpModel) {
        getDataObject({ repository.checkOtp(otpModel) }, _check)
    }

    fun updatePhone(otpModel: CheckOtpModel) {
        getDataObject({ repository.updatePhone(otpModel) }, _updatePhone)
    }
}