package uz.rounded.baqlajon.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.domain.model.UserResponseModel
import uz.rounded.baqlajon.domain.model.auth.login.LoginModel
import uz.rounded.baqlajon.domain.model.auth.otp.CheckOtpModel
import uz.rounded.baqlajon.domain.model.auth.otp.SendOtpModel
import uz.rounded.baqlajon.domain.model.auth.password.ForgotPasswordModel
import uz.rounded.baqlajon.domain.model.auth.register.RegisterModel

interface AuthRepository {
    suspend fun register(request: RegisterModel): Flow<Resource<UserResponseModel>>
    suspend fun login(loginModel: LoginModel): Flow<Resource<UserResponseModel>>
    suspend fun createOtp(createOtpModel: SendOtpModel): Flow<Resource<String>>
    suspend fun createForgetOtp(createOtpModel: SendOtpModel): Flow<Resource<String>>
    suspend fun checkOtp(otpModel: CheckOtpModel): Flow<Resource<Boolean>>
    suspend fun updatePhone(otpModel: CheckOtpModel): Flow<Resource<UserResponseModel>>
    suspend fun forgetPassword(forgotPasswordModel: ForgotPasswordModel): Flow<Resource<UserResponseModel>>
    //    suspend fun recovery(loginModel: LoginModel): Flow<Resource<UserModel>>

}