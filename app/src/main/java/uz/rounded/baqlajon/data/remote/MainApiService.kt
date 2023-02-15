package uz.rounded.baqlajon.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.rounded.baqlajon.data.remote.dto.MainResponseDto
import uz.rounded.baqlajon.data.remote.dto.main.course.GetByIdCourseDto
import uz.rounded.baqlajon.data.remote.dto.main.course.GetCourseDto

interface MainApiService {
    @GET("course")
    suspend fun getAllCourse(): Response<MainResponseDto<List<GetCourseDto>>>

    @GET("course/popular")
    suspend fun getPopular(): Response<MainResponseDto<List<GetCourseDto>>>

    @GET("course/newest")
    suspend fun getNewest(): Response<MainResponseDto<List<GetCourseDto>>>

    @GET("course/{id}")
    suspend fun getByIdCourse(@Path("id") id: String): Response<MainResponseDto<GetByIdCourseDto>>
}