package uz.rounded.baqlajon.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import uz.rounded.baqlajon.data.remote.dto.MainResponseDto
import uz.rounded.baqlajon.data.remote.dto.main.course.GetByIdCourseDto
import uz.rounded.baqlajon.data.remote.dto.main.course.GetCourseDto
import uz.rounded.baqlajon.data.remote.dto.main.course.GetMyCourseDto

interface MainApiService {
    @GET("course")
    suspend fun getAllCourse(): Response<MainResponseDto<List<GetCourseDto>>>

    @GET("course/popular")
    suspend fun getPopular(): Response<MainResponseDto<List<GetCourseDto>>>

    @GET("course/newest")
    suspend fun getNewest(): Response<MainResponseDto<List<GetCourseDto>>>

    @GET("myCourse/all")
    suspend fun getAllMyCourse(): Response<MainResponseDto<List<GetMyCourseDto>>>

    @GET("myCourse/status")
    suspend fun getMyCourseStatus(@Query("status") status: String): Response<MainResponseDto<List<GetMyCourseDto>>>

    @POST("start/{id}")
    suspend fun getStartCourse(@Path("id") id: String): Response<MainResponseDto<Boolean>>

    @GET("course/{id}")
    suspend fun getByIdCourse(@Path("id") id: String): Response<MainResponseDto<GetByIdCourseDto>>
}