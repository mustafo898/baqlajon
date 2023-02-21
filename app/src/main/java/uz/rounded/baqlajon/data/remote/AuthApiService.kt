package uz.rounded.baqlajon.data.remote

import retrofit2.Response
import retrofit2.http.*
import uz.rounded.baqlajon.data.remote.dto.MainResponseDto
import uz.rounded.baqlajon.data.remote.dto.UserResponseDto
import uz.rounded.baqlajon.data.remote.dto.auth.login.LoginRequestDto
import uz.rounded.baqlajon.data.remote.dto.auth.otp.CheckOtp
import uz.rounded.baqlajon.data.remote.dto.auth.otp.SendOtpDto
import uz.rounded.baqlajon.data.remote.dto.auth.password.ForgotPasswordDto
import uz.rounded.baqlajon.data.remote.dto.auth.registration.RegisterDto

interface AuthApiService {

    @POST("user")
    suspend fun registration(@Body request: RegisterDto): Response<MainResponseDto<UserResponseDto>>

    @POST("user/login")
    suspend fun login(@Body login: LoginRequestDto): Response<MainResponseDto<UserResponseDto>>

    @POST("user/register/sendOtp")
    suspend fun createOtp(@Body createOtp: SendOtpDto): Response<MainResponseDto<String>>

    @POST("user/sendOtp")
    suspend fun createForgetOtp(@Body createOtp: SendOtpDto): Response<MainResponseDto<String>>

    @PUT("user/forget")
    suspend fun forgetPassword(@Body forgot: ForgotPasswordDto): Response<MainResponseDto<UserResponseDto>>

    @POST("user/checkOtp")
    suspend fun checkOtp(@Body createOtp: CheckOtp): Response<MainResponseDto<Boolean>>

    @PUT("user/phone")
    suspend fun updatePhone(@Body createOtp: CheckOtp): Response<MainResponseDto<UserResponseDto>>
}