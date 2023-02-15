package uz.rounded.baqlajon.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.domain.model.GetByIdCourseModel
import uz.rounded.baqlajon.domain.model.GetCourseModel

interface MainRepository {
    suspend fun getAllCourse(): Flow<Resource<List<GetCourseModel>>>

    suspend fun getPopularCourse(): Flow<Resource<List<GetCourseModel>>>

    suspend fun getNewestCourse(): Flow<Resource<List<GetCourseModel>>>

    suspend fun getByIdCourse(id: String): Flow<Resource<GetByIdCourseModel>>
}