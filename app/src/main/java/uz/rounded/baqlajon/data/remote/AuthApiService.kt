package uz.rounded.baqlajon.data.remote

import retrofit2.Response
import retrofit2.http.*
import uz.rounded.baqlajon.data.remote.dto.MainResponseDto
import uz.rounded.baqlajon.data.remote.dto.UserDto
import uz.rounded.baqlajon.data.remote.dto.auth.otp.CheckOtp
import uz.rounded.baqlajon.data.remote.dto.auth.otp.SendOtpDto
import uz.rounded.baqlajon.data.remote.dto.auth.password.ForgotPasswordDto
import uz.rounded.baqlajon.data.remote.dto.auth.registration.LoginRequestDto
import uz.rounded.baqlajon.data.remote.dto.auth.registration.RegisterDto

interface AuthApiService {

    @POST("user")
    suspend fun registration(@Body request: RegisterDto): Response<MainResponseDto<UserDto>>

    @POST("user/login")
    suspend fun login(@Body login: LoginRequestDto): Response<MainResponseDto<UserDto>>

//    @POST("student/password")
//    suspend fun recovery(@Body login: LoginRequestDto): Response<MainResponseDto<UserDto>>

    @PUT("user/forget")
    suspend fun forgotPassword(@Body forgot: ForgotPasswordDto): Response<MainResponseDto<String>>

    @POST("user/register/sendOtp")
    suspend fun createOtp(@Body createOtp: SendOtpDto): Response<MainResponseDto<String>>

    @POST("user/checkOtp")
    suspend fun checkOtp(@Body createOtp: CheckOtp): Response<MainResponseDto<Boolean>>

}