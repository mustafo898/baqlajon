package uz.rounded.data.repository.datasource

import retrofit2.Response
import uz.rounded.baqlajon.data.remote.dto.MainResponseDto
import uz.rounded.baqlajon.data.remote.dto.PagingMainDto
import uz.rounded.data.remote.dto.auth.password.PasswordRequestDto
import uz.rounded.baqlajon.data.remote.dto.auth.registration.LoginRequestDto
import uz.rounded.baqlajon.data.remote.dto.auth.sms.OtpRequestDto
import uz.rounded.data.remote.dto.auth.test.DataTestDto
import uz.rounded.data.remote.dto.auth.test.LanguageLevelUpdateDto
import uz.rounded.data.remote.dto.auth.test.LanguageUpdateDto

interface AuthRemoteDatasource {

    suspend fun registration(registrationRequestDto: RegistrationRequestDto): Response<MainResponseDto<UserDto>>

    suspend fun login(loginRequestDto: LoginRequestDto): Response<MainResponseDto<UserDto>>
    suspend fun checkOtp(otpRequestDto: OtpRequestDto): Response<MainResponseDto<Boolean>>
    suspend fun createOtp(otpRequestDto: OtpRequestDto): Response<MainResponseDto<String>>
    suspend fun password(passwordRequestDto: PasswordRequestDto): Response<MainResponseDto<String>>
    suspend fun test(
        page: Int,
        limit: Int
    ): Response<MainResponseDto<PagingMainDto<List<DataTestDto>>>>

    suspend fun recovery(loginModel: LoginRequestDto): Response<MainResponseDto<UserDto>>

    suspend fun updateStudentLang(updateDto: LanguageUpdateDto): Response<MainResponseDto<String>>
    suspend fun updateStudentLangLevel(updateDto: LanguageLevelUpdateDto): Response<MainResponseDto<String>>
}