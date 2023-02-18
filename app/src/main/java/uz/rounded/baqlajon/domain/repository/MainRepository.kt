package uz.rounded.baqlajon.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.domain.model.*

interface MainRepository {
    suspend fun getAllCourse(): Flow<Resource<List<GetCourseModel>>>

    suspend fun getPopularCourse(): Flow<Resource<List<GetCourseModel>>>

    suspend fun getNewestCourse(): Flow<Resource<List<GetCourseModel>>>

    suspend fun getAllMyCourse(): Flow<Resource<List<GetMyCourseModel>>>

    suspend fun getMyCourseStatus(status: String): Flow<Resource<List<GetMyCourseModel>>>

    suspend fun getStartCourse(id: String): Flow<Resource<Boolean>>

    suspend fun getByIdCourse(id: String): Flow<Resource<GetByIdCourseModel>>
    suspend fun getByIdVideo(id: String): Flow<Resource<VideoModel>>
    suspend fun finishVideo(id: String): Flow<Resource<Boolean>>
    suspend fun createComment(id: String,requestCommentModel: RequestCommentModel): Flow<Resource<CommentModel>>
}