package uz.rounded.baqlajon.data.repository


import uz.rounded.baqlajon.data.common.ResponseHandler
import uz.rounded.baqlajon.data.remote.MainApiService
import uz.rounded.baqlajon.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainApiService: MainApiService
) : MainRepository, ResponseHandler() {


}