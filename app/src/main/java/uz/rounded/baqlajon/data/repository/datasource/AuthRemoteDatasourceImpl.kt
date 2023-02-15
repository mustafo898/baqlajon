package uz.rounded.data.repository.datasource

import retrofit2.Response
import uz.rounded.baqlajon.data.remote.AuthService
import uz.rounded.baqlajon.data.remote.dto.MainResponseDto
import uz.rounded.baqlajon.data.remote.dto.PagingMainDto
import uz.rounded.data.remote.dto.auth.password.PasswordRequestDto
import uz.rounded.baqlajon.data.remote.dto.auth.registration.LoginRequestDto
import uz.rounded.baqlajon.data.remote.dto.auth.sms.OtpRequestDto
import uz.rounded.data.remote.dto.auth.test.DataTestDto
import uz.rounded.data.remote.dto.auth.test.LanguageLevelUpdateDto
import uz.rounded.data.remote.dto.auth.test.LanguageUpdateDto
import javax.inject.Inject

class AuthRemoteDatasourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDatasource {
    override suspend fun registration(registrationRequestDto: RegistrationRequestDto): Response<MainResponseDto<UserDto>> {
        return authService.registration(registrationRequestDto)
    }

    override suspend fun login(loginRequestDto: LoginRequestDto): Response<MainResponseDto<UserDto>> {
        return authService.login(loginRequestDto)
    }

    override suspend fun checkOtp(otpRequestDto: OtpRequestDto): Response<MainResponseDto<Boolean>> {
        return authService.checkOtp(otpRequestDto)
    }

    override suspend fun createOtp(otpRequestDto: OtpRequestDto): Response<MainResponseDto<String>> {
        return authService.createOtp(otpRequestDto)
    }

    override suspend fun password(passwordRequestDto: PasswordRequestDto): Response<MainResponseDto<String>> {
        return authService.password(passwordRequestDto)
    }

    override suspend fun test(
        page: Int,
        limit: Int
    ): Response<MainResponseDto<PagingMainDto<List<DataTestDto>>>> {
        return authService.test(page, limit)
    }

    override suspend fun recovery(loginModel: LoginRequestDto): Response<MainResponseDto<UserDto>> {
        return authService.recovery(loginModel)
    }

    override suspend fun updateStudentLang(updateDto: LanguageUpdateDto): Response<MainResponseDto<String>> {
        return authService.updateLang(updateDto)
    }

    override suspend fun updateStudentLangLevel(updateDto: LanguageLevelUpdateDto): Response<MainResponseDto<String>> {
        return authService.updateLangLevel(updateDto)
    }
}
