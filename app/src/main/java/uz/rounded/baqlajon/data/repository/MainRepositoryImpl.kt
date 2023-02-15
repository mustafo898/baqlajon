package uz.rounded.baqlajon.data.repository


import kotlinx.coroutines.flow.Flow
import uz.rounded.baqlajon.data.common.ResponseHandler
import uz.rounded.baqlajon.data.mapper.toModel
import uz.rounded.baqlajon.data.remote.MainApiService
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.domain.model.GetByIdCourseModel
import uz.rounded.baqlajon.domain.model.GetCourseModel
import uz.rounded.baqlajon.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainApiService: MainApiService
) : MainRepository, ResponseHandler() {

    override suspend fun getAllCourse(): Flow<Resource<List<GetCourseModel>>> = loadResult({
        mainApiService.getAllCourse()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getPopularCourse(): Flow<Resource<List<GetCourseModel>>> = loadResult({
        mainApiService.getPopular()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getNewestCourse(): Flow<Resource<List<GetCourseModel>>> = loadResult({
        mainApiService.getNewest()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getByIdCourse(id: String): Flow<Resource<GetByIdCourseModel>> =
        loadResult({
            mainApiService.getByIdCourse(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })
}