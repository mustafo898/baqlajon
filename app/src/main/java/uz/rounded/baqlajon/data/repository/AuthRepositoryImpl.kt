package uz.rounded.baqlajon.data.repository

import kotlinx.coroutines.flow.Flow
import uz.rounded.baqlajon.data.common.ResponseHandler
import uz.rounded.baqlajon.data.mapper.toModel
import uz.rounded.baqlajon.data.remote.AuthApiService
import uz.rounded.baqlajon.data.remote.dto.auth.login.LoginRequestDto
import uz.rounded.baqlajon.data.remote.dto.auth.otp.CheckOtp
import uz.rounded.baqlajon.data.remote.dto.auth.otp.SendOtpDto
import uz.rounded.baqlajon.data.remote.dto.auth.password.ForgotPasswordDto
import uz.rounded.baqlajon.data.remote.dto.auth.registration.RegisterDto
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.domain.model.UserResponseModel
import uz.rounded.baqlajon.domain.model.auth.login.LoginModel
import uz.rounded.baqlajon.domain.model.auth.otp.CheckOtpModel
import uz.rounded.baqlajon.domain.model.auth.otp.SendOtpModel
import uz.rounded.baqlajon.domain.model.auth.password.ForgotPasswordModel
import uz.rounded.baqlajon.domain.model.auth.register.RegisterModel
import uz.rounded.baqlajon.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRepository, ResponseHandler() {
    override suspend fun register(request: RegisterModel): Flow<Resource<UserResponseModel>> =
        loadResult({
            authApiService.registration(
                RegisterDto(
                    firstName = request.firstName,
                    lastName = request.lastName,
                    image = request.image,
                    password = request.password,
                    phoneNumber = request.phoneNumber,
                    promocode = request.promocode
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun login(loginModel: LoginModel): Flow<Resource<UserResponseModel>> =
        loadResult({
            authApiService.login(
                LoginRequestDto(
                    phoneNumber = loginModel.phoneNumber, password = loginModel.password
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

    override suspend fun createForgetOtp(createOtpModel: SendOtpModel): Flow<Resource<String>> =
        loadResult({
            authApiService.createForgetOtp(
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
                otp = otpModel.otp, phoneNumber = otpModel.phoneNumber
            )
        )
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun updatePhone(otpModel: CheckOtpModel): Flow<Resource<UserResponseModel>> =
        loadResult({
            authApiService.updatePhone(
                CheckOtp(
                    otp = otpModel.otp, phoneNumber = otpModel.phoneNumber
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun forgetPassword(forgotPasswordModel: ForgotPasswordModel): Flow<Resource<UserResponseModel>> =
        loadResult({
            authApiService.forgetPassword(
                ForgotPasswordDto(
                    phoneNumber = forgotPasswordModel.phoneNumber,
                    password = forgotPasswordModel.password
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

//    override suspend fun recovery(loginModel: LoginModel): Flow<Resource<UserModel>> =
//    loadResult(
//    {
//        authApiService.forgotPassword(
//            ForgotPasswordDto(
//                phoneNumber = loginModel._phoneNumber
//            )
//        )
//    },
//    {
//        data, flow ->
//        try {
//            flow.emit(Resource.Success(data))
//        } catch (e: Exception) {
//            flow.emit(Resource.Error(e.message.toString()))
//        }
//    })
}