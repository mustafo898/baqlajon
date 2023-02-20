package uz.rounded.baqlajon.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.domain.model.UserModel
import uz.rounded.baqlajon.domain.model.UserResponseModel
import uz.rounded.baqlajon.domain.model.auth.login.LoginModel
import uz.rounded.baqlajon.domain.model.auth.otp.CheckOtpModel
import uz.rounded.baqlajon.domain.model.auth.otp.SendOtpModel
import uz.rounded.baqlajon.domain.model.auth.register.RegisterModel

interface AuthRepository {
    suspend fun register(request: RegisterModel): Flow<Resource<UserResponseModel>>
    suspend fun login(loginModel: LoginModel): Flow<Resource<UserResponseModel>>
    suspend fun createOtp(createOtpModel: SendOtpModel): Flow<Resource<String>>
    suspend fun checkOtp(otpModel: CheckOtpModel): Flow<Resource<Boolean>>
    suspend fun password(passwordModel: SendOtpModel): Flow<Resource<String>>
//    suspend fun recovery(loginModel: LoginModel): Flow<Resource<UserModel>>

}