package uz.rounded.baqlajon.data.remote

import retrofit2.Response
import retrofit2.http.*
import uz.rounded.baqlajon.data.remote.dto.MainResponseDto
import uz.rounded.baqlajon.data.remote.dto.main.course.*

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
    suspend fun startCourse(@Path("id") id: String): Response<MainResponseDto<Boolean>>

    @GET("course/{id}")
    suspend fun getByIdCourse(@Path("id") id: String): Response<MainResponseDto<GetByIdCourseDto>>

    @GET("video/{id}")
    suspend fun getByIdVideo(@Path("id") id: String): Response<MainResponseDto<VideoDto>>

    @POST("video/finish/{id}")
    suspend fun finishVideo(@Path("id") id: String): Response<MainResponseDto<Boolean>>

    @POST("comment/{id}")
    suspend fun createComment(
        @Path("id") id: String,
        @Body requestCommentDto: RequestCommentDto
    ): Response<MainResponseDto<CommentDto>>
}