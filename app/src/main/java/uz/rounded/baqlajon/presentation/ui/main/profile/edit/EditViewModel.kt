package uz.rounded.baqlajon.presentation.ui.main.profile.edit

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.MultipartBody
import uz.rounded.baqlajon.domain.model.DataModel
import uz.rounded.baqlajon.domain.model.main.profile.UpdateUserRequestModel
import uz.rounded.baqlajon.domain.repository.AuthRepository
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.common.UIObjectState
import uz.rounded.baqlajon.presentation.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {
    private val _user = MutableStateFlow(UIObjectState<DataModel>())
    val user = _user.asStateFlow()

    fun getUser() {
        getDataObject({ mainRepository.getProfile() }, _user)
    }

    private val _updateUser = MutableStateFlow(UIObjectState<DataModel>())
    val updateUser = _updateUser.asStateFlow()

    fun updateUser(updateUserRequestModel: UpdateUserRequestModel) {
        getDataObject({ mainRepository.updateUser(updateUserRequestModel) }, _updateUser)
    }

    private val _uploadImage = MutableStateFlow(UIObjectState<String>())
    val uploadImage = _uploadImage.asStateFlow()

    fun uploadImage(file: MultipartBody.Part) {
        getDataObject({ mainRepository.uploadImage(file) }, _uploadImage)
    }
}