package uz.rounded.baqlajon.data.remote

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import uz.rounded.baqlajon.data.remote.dto.DataDto
import uz.rounded.baqlajon.data.remote.dto.MainResponseDto
import uz.rounded.baqlajon.data.remote.dto.main.course.*
import uz.rounded.baqlajon.data.remote.dto.main.gift.GetGiftDto
import uz.rounded.baqlajon.data.remote.dto.main.profile.UpdateUserRequestDto

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

    @GET("myCourse/start/{id}")
    suspend fun startCourse(@Path("id") id: String): Response<MainResponseDto<Boolean>>

    @GET("course/{id}")
    suspend fun getByIdCourse(@Path("id") id: String): Response<MainResponseDto<GetByIdCourseDto>>

    @GET("video/{id}")
    suspend fun getByIdVideo(@Path("id") id: String): Response<MainResponseDto<ContentDto>>

    @GET("video/finish/{id}")
    suspend fun finishVideo(@Path("id") id: String): Response<MainResponseDto<Boolean>>

    @GET("gift/buy/{id}")
    suspend fun buyGift(@Path("id") id: String): Response<MainResponseDto<String>>

    @Multipart
    @POST("image/upload")
    suspend fun uploadImage(@Part file: MultipartBody.Part): Response<MainResponseDto<String>>

    @GET("gift")
    suspend fun getGift(): Response<MainResponseDto<List<GetGiftDto>>>

    @GET("user")
    suspend fun getProfile(): Response<MainResponseDto<DataDto>>

    @PUT("user")
    suspend fun updateUser(@Body updateUserRequestDto: UpdateUserRequestDto): Response<MainResponseDto<DataDto>>

    @POST("comment/{id}")
    suspend fun createComment(
        @Path("id") id: String,
        @Body requestCommentDto: RequestCommentDto
    ): Response<MainResponseDto<CommentDto>>

    @GET("course/paging")
    suspend fun searchAllCourse(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int,
        @Query("search") search: String
    ): Response<MainResponseDto<List<GetCourseDto>>>
}