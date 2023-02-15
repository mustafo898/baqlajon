package uz.rounded.baqlajon.data.repository

import uz.rounded.baqlajon.data.common.ResponseHandler
import uz.rounded.baqlajon.data.remote.AuthApiService
import uz.rounded.baqlajon.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRepository, ResponseHandler() {


}