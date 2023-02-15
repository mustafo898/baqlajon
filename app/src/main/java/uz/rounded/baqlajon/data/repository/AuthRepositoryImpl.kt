package uz.rounded.baqlajon.data.repository

import kotlinx.coroutines.flow.Flow
import uz.rounded.data.mapper.toModel
import uz.rounded.data.mapper.toUser
import uz.rounded.data.remote.dto.auth.password.PasswordRequestDto
import uz.rounded.baqlajon.data.remote.dto.auth.registration.LoginRequestDto
import uz.rounded.baqlajon.data.remote.dto.auth.sms.OtpRequestDto
import uz.rounded.data.remote.dto.auth.test.LanguageLevelUpdateDto
import uz.rounded.data.remote.dto.auth.test.LanguageUpdateDto
import uz.rounded.data.common.ResponseHandler
import uz.rounded.data.repository.datasource.AuthRemoteDatasource
import uz.rounded.domain.common.Resource
import uz.rounded.domain.model.UserProfile
import uz.rounded.domain.model.auth.login.LoginModel
import uz.rounded.domain.model.auth.password.PasswordModel
import uz.rounded.domain.model.auth.registration.RegistrationModel
import uz.rounded.domain.model.auth.sms.OtpModel
import uz.rounded.domain.model.auth.test.DataModel
import uz.rounded.domain.model.auth.test.LanguageLevelUpdateModel
import uz.rounded.domain.model.auth.test.LanguageUpdateModel
import uz.rounded.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDatasource: AuthRemoteDatasource
) : AuthRepository, ResponseHandler() {

    override suspend fun registration(registrationModel: RegistrationModel): Flow<Resource<UserProfile>> =
        loadResult({
            remoteDatasource.registration(
                RegistrationRequestDto(
                    address = registrationModel.address,
                    firstName = registrationModel.firstName,
                    lastName = registrationModel.lastName,
                    phoneNumber = registrationModel.phoneNumber
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toUser()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun login(loginModel: LoginModel): Flow<Resource<UserProfile>> =
        loadResult({
            remoteDatasource.login(
                LoginRequestDto(
                    phoneNumber = loginModel.phoneNumber,
                    password = loginModel.password
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toUser()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun createOtp(createOtpModel: OtpModel): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.createOtp(
                OtpRequestDto(
                    phoneNumber = createOtpModel.phoneNumber,
                    otp = createOtpModel.otp
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun checkOtp(otpModel: OtpModel): Flow<Resource<Boolean>> = loadResult({
        remoteDatasource.checkOtp(
            OtpRequestDto(
                phoneNumber = otpModel.phoneNumber,
                otp = otpModel.otp
            )
        )
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun password(passwordModel: PasswordModel): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.password(
                PasswordRequestDto(
                    password = passwordModel.password,
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun test(page: Int, limit: Int): Flow<Resource<List<DataModel>>> = loadResult({
        remoteDatasource.test(page, limit)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun recovery(loginModel: LoginModel): Flow<Resource<UserProfile>> =
        loadResult({
            remoteDatasource.recovery(
                LoginRequestDto(
                    phoneNumber = loginModel.phoneNumber,
                    password = loginModel.password
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toUser()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun updateLang(languageUpdateModel: LanguageUpdateModel): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.updateStudentLang(
                LanguageUpdateDto(
                    languages = languageUpdateModel.languages,
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun updateLangLevel(languageUpdateModel: LanguageLevelUpdateModel): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.updateStudentLangLevel(
                LanguageLevelUpdateDto(
                    languageLevel = languageUpdateModel.languageLevel,
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })
}