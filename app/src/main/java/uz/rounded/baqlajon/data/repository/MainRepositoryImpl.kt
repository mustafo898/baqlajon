package uz.rounded.baqlajon.data.repository


import android.util.Log
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import uz.rounded.baqlajon.data.common.ResponseHandler
import uz.rounded.baqlajon.data.mapper.toModel
import uz.rounded.baqlajon.data.paging.createPager
import uz.rounded.baqlajon.data.remote.MainApiService
import uz.rounded.baqlajon.data.remote.dto.main.profile.UpdateUserRequestDto
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.domain.model.DataModel
import uz.rounded.baqlajon.domain.model.main.course.*
import uz.rounded.baqlajon.domain.model.main.gift.GetGiftModel
import uz.rounded.baqlajon.domain.model.main.profile.UpdateUserRequestModel
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

    override suspend fun getAllMyCourse(): Flow<Resource<List<GetMyCourseModel>>> = loadResult({
        mainApiService.getAllMyCourse()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getMyCourseStatus(status: String): Flow<Resource<List<GetMyCourseModel>>> =
        loadResult({
            mainApiService.getMyCourseStatus(status)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getStartCourse(id: String): Flow<Resource<Boolean>> =
        loadResult({
            mainApiService.startCourse(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getGift(): Flow<Resource<List<GetGiftModel>>> =
        loadResult({
            mainApiService.getGift()
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getProfile(): Flow<Resource<DataModel>> =
        loadResult({
            mainApiService.getProfile()
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun updateUser(updateUserRequestModel: UpdateUserRequestModel): Flow<Resource<DataModel>> =
        loadResult({
            mainApiService.updateUser(
                UpdateUserRequestDto(
                    updateUserRequestModel.firstName,
                    updateUserRequestModel.image,
                    updateUserRequestModel.lastName,
                    updateUserRequestModel.password,
                    updateUserRequestModel.phoneNumber
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun uploadImage(file: MultipartBody.Part): Flow<Resource<String>> =
        loadResult({
            mainApiService.uploadImage(file)
        }, { data, flow ->
            try {
                Log.d("EEEEEE", "uploadImage: $file")
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                Log.d("EEEEEE", "error: $e")
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

    override suspend fun getByIdVideo(id: String): Flow<Resource<VideoModel>> =
        loadResult({
            mainApiService.getByIdVideo(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun finishVideo(id: String): Flow<Resource<Boolean>> =
        loadResult({
            mainApiService.finishVideo(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun buyGift(id: String): Flow<Resource<String>> =
        loadResult({
            mainApiService.buyGift(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun createComment(
        id: String,
        requestCommentModel: RequestCommentModel
    ): Flow<Resource<CommentModel>> =
        loadResult({
            mainApiService.createComment(id, requestCommentModel.toModel())
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun searchAllCourse(search: String): Flow<PagingData<GetCourseModel>> {
        return createPager { page ->
            val response = mainApiService.searchAllCourse(page = page, search = search)
            response.body()?.data!!.map {
                it.toModel()
            }
        }.flow
    }
}