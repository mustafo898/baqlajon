package uz.rounded.baqlajon.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.domain.model.DataModel
import uz.rounded.baqlajon.domain.model.main.course.*
import uz.rounded.baqlajon.domain.model.main.gift.GetGiftModel
import uz.rounded.baqlajon.domain.model.main.profile.UpdateUserRequestModel

interface MainRepository {
    suspend fun getAllCourse(): Flow<Resource<List<GetCourseModel>>>

    suspend fun getPopularCourse(): Flow<Resource<List<GetCourseModel>>>

    suspend fun getNewestCourse(): Flow<Resource<List<GetCourseModel>>>

    suspend fun getAllMyCourse(): Flow<Resource<List<GetMyCourseModel>>>

    suspend fun getMyCourseStatus(status: String): Flow<Resource<List<GetMyCourseModel>>>

    suspend fun getStartCourse(id: String): Flow<Resource<Boolean>>

    suspend fun getGift(): Flow<Resource<List<GetGiftModel>>>

    suspend fun getProfile(): Flow<Resource<DataModel>>

    suspend fun updateUser(updateUserRequestModel: UpdateUserRequestModel): Flow<Resource<DataModel>>

    suspend fun uploadImage(file: MultipartBody.Part): Flow<Resource<String>>

    suspend fun getByIdCourse(id: String): Flow<Resource<GetByIdCourseModel>>

    suspend fun getByIdVideo(id: String): Flow<Resource<ContentModel>>

    suspend fun finishVideo(id: String): Flow<Resource<Boolean>>

    suspend fun buyGift(id: String): Flow<Resource<String>>

    suspend fun createComment(
        id: String,
        requestCommentModel: RequestCommentModel
    ): Flow<Resource<CommentModel>>

    suspend fun searchAllCourse(search: String): Flow<PagingData<GetCourseModel>>
}