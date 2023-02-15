package uz.rounded.baqlajon.data.repository

import kotlinx.coroutines.flow.Flow
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.data.common.ResponseHandler
import uz.rounded.baqlajon.data.mapper.toModel
import uz.rounded.baqlajon.data.remote.AuthApiService
import uz.rounded.baqlajon.data.remote.dto.auth.otp.CheckOtp
import uz.rounded.baqlajon.data.remote.dto.auth.otp.SendOtpDto
import uz.rounded.baqlajon.data.remote.dto.auth.password.ForgotPasswordDto
import uz.rounded.baqlajon.data.remote.dto.auth.registration.LoginRequestDto
import uz.rounded.baqlajon.data.remote.dto.auth.registration.RegisterDto
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.domain.model.UserModel
import uz.rounded.baqlajon.domain.model.auth.login.LoginModel
import uz.rounded.baqlajon.domain.model.auth.otp.CheckOtpModel
import uz.rounded.baqlajon.domain.model.auth.otp.SendOtpModel
import uz.rounded.baqlajon.domain.model.auth.register.RegisterModel
import uz.rounded.baqlajon.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService, private val shared: SharedPreference
) : AuthRepository, ResponseHandler() {
    override suspend fun register(request: RegisterModel): Flow<Resource<UserModel>> = loadResult({
        authApiService.registration(
            RegisterDto(
                firstName = request.firstName,
                lastName = request.lastName,
                image = request.image,
                password = request.password,
                phoneNumber = request.phoneNumber
            )
        )
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.toModel()))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun login(loginModel: LoginModel): Flow<Resource<UserModel>> = loadResult({
        authApiService.login(
            LoginRequestDto(
                phoneNumber = loginModel._phoneNumber, password = loginModel.password
            )
        )
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.toModel()))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun createOtp(createOtpModel: SendOtpModel): Flow<Resource<String>> =
        loadResult({
            authApiService.createOtp(
                SendOtpDto(
                    phoneNumber = createOtpModel.phoneNumber
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun checkOtp(otpModel: CheckOtpModel): Flow<Resource<Boolean>> = loadResult({
        authApiService.checkOtp(
            CheckOtp(
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

    override suspend fun password(passwordModel: SendOtpModel): Flow<Resource<String>> =
        loadResult({
            authApiService.forgotPassword(
                ForgotPasswordDto(
                    phoneNumber = passwordModel.phoneNumber
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