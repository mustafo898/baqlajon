package uz.rounded.baqlajon.presentation.ui.start.auth.reset_password

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.rounded.baqlajon.domain.model.UserResponseModel
import uz.rounded.baqlajon.domain.model.auth.password.ForgotPasswordModel
import uz.rounded.baqlajon.domain.repository.AuthRepository
import uz.rounded.baqlajon.presentation.common.UIObjectState
import uz.rounded.baqlajon.presentation.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val service: AuthRepository
) : BaseViewModel() {

    private val _password = MutableStateFlow(UIObjectState<UserResponseModel>())
    val password = _password.asStateFlow()

    fun forgotPassword(passwordModel: ForgotPasswordModel) {
        getDataObject({ service.forgetPassword(passwordModel) }, _password)
    }
}