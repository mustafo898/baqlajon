package uz.rounded.baqlajon.presentation.ui.start.auth.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.rounded.baqlajon.domain.model.UserResponseModel
import uz.rounded.baqlajon.domain.model.auth.login.LoginModel
import uz.rounded.baqlajon.domain.repository.AuthRepository
import uz.rounded.baqlajon.presentation.common.UIObjectState
import uz.rounded.baqlajon.presentation.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel() {

    private val _login = MutableStateFlow(UIObjectState<UserResponseModel>())
    val login = _login.asStateFlow()

    fun login(registrationModel: LoginModel) {
        getDataObject({ repository.login(registrationModel) }, _login)
    }

}