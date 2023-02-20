package uz.rounded.baqlajon.presentation.ui.start.auth.reset_password

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
import uz.rounded.baqlajon.domain.model.auth.password.ForgotPasswordModel
import uz.rounded.baqlajon.domain.repository.AuthRepository
import uz.rounded.baqlajon.presentation.common.UIObjectState
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val service: AuthRepository
) : ViewModel() {

    private val _password = MutableStateFlow(UIObjectState<UserResponseModel>())
    val password = _password.asStateFlow()

    fun forgotPassword(passwordModel: ForgotPasswordModel) {
        viewModelScope.launch {
            service.forgetPassword(passwordModel).onEach {
                when (it) {
                    is Resource.Error -> {
                        _password.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _password.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _password.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }
}