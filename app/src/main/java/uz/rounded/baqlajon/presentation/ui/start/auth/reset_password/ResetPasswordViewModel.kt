package uz.rounded.baqlajon.presentation.ui.start.auth.reset_password

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.rounded.baqlajon.domain.model.UserModel
import uz.rounded.baqlajon.domain.repository.AuthRepository
import uz.rounded.baqlajon.presentation.common.UIObjectState
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val service: AuthRepository
) : ViewModel() {

    private val _password = MutableStateFlow(UIObjectState<String>())
    val password = _password.asStateFlow()

//    fun password(passwordModel: PasswordRequestModel) {
//        viewModelScope.launch {
//            service.password(passwordModel).onEach {
//                when (it) {
//                    is Resource.Error -> {
//                        _password.value = UIObjectState(it.message ?: "")
//                    }
//                    is Resource.Loading -> {
//                        _password.value = UIObjectState(isLoading = true)
//                    }
//                    is Resource.Success -> {
//                        _password.value = UIObjectState(data = it.data)
//                    }
//                }
//            }.launchIn(CoroutineScope(Dispatchers.IO))
//        }
//    }

    private val _recovery = MutableStateFlow(UIObjectState<UserModel>())
    val recovery = _recovery.asStateFlow()

//    fun recoveryPassword(registrationModel: LoginModel) {
//        viewModelScope.launch {
//            service.recovery(registrationModel).onEach {
//                when (it) {
//                    is Resource.Error -> {
//                        _recovery.value = UIObjectState(it.message ?: "")
//                    }
//                    is Resource.Loading -> {
//                        _recovery.value = UIObjectState(isLoading = true)
//                    }
//                    is Resource.Success -> {
//                        _recovery.value = UIObjectState(data = it.data)
//                    }
//                }
//            }.launchIn(CoroutineScope(Dispatchers.IO))
//        }
//    }
}