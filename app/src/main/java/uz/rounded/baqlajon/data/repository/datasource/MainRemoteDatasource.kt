package uz.rounded.data.repository.datasource

import okhttp3.MultipartBody
import retrofit2.Response
import uz.rounded.data.local.database.model.DbDto
import uz.rounded.baqlajon.data.remote.dto.MainResponseDto
import uz.rounded.baqlajon.data.remote.dto.PagingMainDto
import uz.rounded.data.remote.dto.main.GrammerDto
import uz.rounded.data.remote.dto.main.category.AllCategoryDto
import uz.rounded.data.remote.dto.main.courses.comment.CreateCourseCommentDto
import uz.rounded.data.remote.dto.main.courses.comment.GetCourseCommentDto
import uz.rounded.data.remote.dto.main.courses.courses_lang.GetCoursesByLangDto
import uz.rounded.data.remote.dto.main.courses.free_time.GetFreeTimeDto
import uz.rounded.data.remote.dto.main.courses.group.GetGroupByLangListDto
import uz.rounded.data.remote.dto.main.courses.group.GroupDetailDto
import uz.rounded.data.remote.dto.main.courses.language.LanguageDto
import uz.rounded.data.remote.dto.main.courses.lesson_finish.LessonFinishRequestDto
import uz.rounded.data.remote.dto.main.courses.lesson_start.LessonStartRequestDto
import uz.rounded.data.remote.dto.main.courses.lessons.*
import uz.rounded.data.remote.dto.main.courses.lessons.homework.GetHomeworkDto
import uz.rounded.data.remote.dto.main.courses.listening.GetListeningDto
import uz.rounded.data.remote.dto.main.courses.my_courses.GetMyCoursesDto
import uz.rounded.data.remote.dto.main.courses.order.OrderCourseRequestDto
import uz.rounded.data.remote.dto.main.courses.order.OrderGroupRequestDto
import uz.rounded.data.remote.dto.main.courses.platform.GetPlatformDetailsZoomDto
import uz.rounded.data.remote.dto.main.courses.sections.LabelDto
import uz.rounded.data.remote.dto.main.courses.sections.SectionsDto
import uz.rounded.data.remote.dto.main.courses.teacher.GetTeacherByIdDto
import uz.rounded.data.remote.dto.main.courses.teacher.TeacherResponseDto
import uz.rounded.data.remote.dto.main.forum.comments.GetPostCommentsDto
import uz.rounded.data.remote.dto.main.forum.complain.ComplainDto
import uz.rounded.data.remote.dto.main.forum.create_post.CreatePostOnForumRequestDto
import uz.rounded.data.remote.dto.main.forum.paging.DataPagingDto
import uz.rounded.data.remote.dto.main.forum.rate.RateCommentRequestDto
import uz.rounded.data.remote.dto.main.forum.reply.ReplyToPostRequestDto
import uz.rounded.data.remote.dto.main.home.news.NewsPaginationDto
import uz.rounded.data.remote.dto.main.home.video.HomeVideoDto
import uz.rounded.data.remote.dto.main.home.video.VideoDataDto
import uz.rounded.data.remote.dto.main.lesson_vidio.LessonVidioDto
import uz.rounded.data.remote.dto.main.news.NewsDto
import uz.rounded.data.remote.dto.main.podcast.PodcastCategoryDtoItem
import uz.rounded.data.remote.dto.main.podcast.podcastListByCategory.PodcastDataDto
import uz.rounded.data.remote.dto.main.podcast.podcastListByCategory.podcastInfo.PodcastInfoDto
import uz.rounded.data.remote.dto.main.saved.GetSavedDto
import uz.rounded.data.remote.dto.main.saved.SavedCountDto
import uz.rounded.data.remote.dto.main.saved.saved_lesson.ObjectSavedLessonDto
import uz.rounded.data.remote.dto.main.settings.about.AboutDto
import uz.rounded.data.remote.dto.main.settings.contact.GetContactsDto
import uz.rounded.data.remote.dto.main.settings.device.GetDevicesDto
import uz.rounded.data.remote.dto.main.settings.faq.GetFaqDto
import uz.rounded.data.remote.dto.main.speaking.SpeakingDto
import uz.rounded.data.remote.dto.main.updateProfile.UpdateActiveDto
import uz.rounded.data.remote.dto.main.updateProfile.UpdateProfile
import uz.rounded.data.remote.dto.main.updateProfile.payment.history.PaymentHistoryOutcomeDto
import uz.rounded.data.remote.dto.main.updateProfile.payment.history.PaymentIncomeHistoryDto
import uz.rounded.data.remote.dto.main.vocabulary.SavedVDto
import uz.rounded.data.remote.dto.main.vocabulary.VocabularyDto
import uz.rounded.domain.model.main.device.DeviceModel
import uz.rounded.domain.model.main.firebase.FireBaseModel

interface MainRemoteDatasource {

    suspend fun getUserData(): Response<MainResponseDto<UserDto>>

    /** ------------------------------------          Forum         ------------------------------------------------------------------------  */

    suspend fun getForumByPaging(
        page: Int,
    ): Response<MainResponseDto<PagingMainDto<List<DataPagingDto>>>>

    suspend fun getPodcastInfoDb(): List<DbDto>

    suspend fun addPodcastInfoDb(dto: DbDto)

    suspend fun getForumByPagingCategory(
        page: Int,
        id: String
    ): Response<MainResponseDto<PagingMainDto<List<DataPagingDto>>>>

    suspend fun searchForum(
        page: Int, search: String
    ): Response<MainResponseDto<PagingMainDto<List<DataPagingDto>>>>

    suspend fun getForumById(
        id: String,
    ): Response<MainResponseDto<DataPagingDto>>

    suspend fun getForumPostComments(id: String): Response<MainResponseDto<List<GetPostCommentsDto>>>

    suspend fun getSelectedPosts(type: String): Response<MainResponseDto<List<DataPagingDto>>>

//    suspend fun getUnselectedPosts(): Response<MainResponseDto<List<DataPagingDto>>>

    suspend fun chooseAnswer(id: String): Response<MainResponseDto<Boolean>>

    suspend fun rateComment(rateCommentRequestDto: RateCommentRequestDto): Response<MainResponseDto<Any>>

    suspend fun ratePost(id: String): Response<MainResponseDto<String>>

    suspend fun replyPost(replyToPostRequestDto: ReplyToPostRequestDto): Response<MainResponseDto<String>>

    suspend fun complainToComment(complainDto: ComplainDto): Response<MainResponseDto<String>>

    suspend fun createPostOnForum(createPostOnForumRequestDto: CreatePostOnForumRequestDto): Response<MainResponseDto<String>>

    suspend fun getForumCategory(): Response<MainResponseDto<List<AllCategoryDto>>>

    suspend fun getFaq(): Response<MainResponseDto<List<GetFaqDto>>>

    suspend fun getCourseFaq(id: String): Response<MainResponseDto<List<GetFaqDto>>>

    suspend fun getAbout(): Response<MainResponseDto<AboutDto>>

    suspend fun getContact(): Response<MainResponseDto<GetContactsDto>>

    suspend fun getPrivacy(): Response<MainResponseDto<AboutDto>>

    suspend fun getDevices(): Response<MainResponseDto<List<GetDevicesDto>>>

    /** ------------------------------------          Forum         ------------------------------------------------------------------------  */

    /** ------------------------------------          Course         ------------------------------------------------------------------------  */

    suspend fun createCourseComment(createCourseCommentDto: CreateCourseCommentDto): Response<MainResponseDto<GetCourseCommentDto>>

    suspend fun getFreeTime(id: String): Response<MainResponseDto<GetFreeTimeDto>>

    suspend fun getCourseComments(id: String): Response<MainResponseDto<List<GetCourseCommentDto>>>
    suspend fun getGroupComments(id: String): Response<MainResponseDto<List<GetCourseCommentDto>>>

    suspend fun getCoursesByLang(
        page: Int, languageId: String
    ): Response<MainResponseDto<PagingMainDto<List<GetCoursesByLangDto>>>>

    suspend fun getIndividualTeachers(
        page: Int, languageId: String
    ): Response<MainResponseDto<PagingMainDto<List<TeacherResponseDto>>>>

    suspend fun getMyCourses(id: String): Response<MainResponseDto<List<GetMyCoursesDto>>>

    suspend fun orderCourse(body: OrderCourseRequestDto): Response<MainResponseDto<Boolean>>

    suspend fun orderGroup(body: OrderGroupRequestDto): Response<MainResponseDto<Boolean>>

    suspend fun getSections(
        page: Int, id: String
    ): Response<MainResponseDto<PagingMainDto<List<SectionsDto>>>>

    suspend fun getPlatformZoomByID(id: String): Response<MainResponseDto<GetPlatformDetailsZoomDto>>

    suspend fun updateSpeaking(updateSpeaking: UpdateSpeakingDto): Response<MainResponseDto<Boolean>>

    suspend fun updateHomeWork(updateHomeWorkDto: UpdateHomeWorkDto): Response<MainResponseDto<Boolean>>

    suspend fun updateListening(updateListeningDto: UpdateListeningDto): Response<MainResponseDto<Boolean>>

    suspend fun updateGrammar(updateGrammarDto: UpdateGrammarDto): Response<MainResponseDto<Boolean>>

    suspend fun updateVocabulary(updateVocabularyDto: UpdateVocabularyDto): Response<MainResponseDto<Boolean>>

    suspend fun getLessonsById(id: String): Response<MainResponseDto<List<GetLessonsByIdDto>>>

    suspend fun getIndividualById(id: String): Response<MainResponseDto<GetTeacherByIdDto>>

    suspend fun getListening(id: String): Response<MainResponseDto<List<GetListeningDto>>>

    suspend fun getMembers(id: String): Response<MainResponseDto<List<GetMembersDto>>>

    suspend fun lessonStart(lessonStartRequestDto: LessonStartRequestDto): Response<MainResponseDto<String>>

    suspend fun lessonFinish(lessonFinishRequestDto: LessonFinishRequestDto): Response<MainResponseDto<Any>>

    suspend fun getGroupCourseById(
        id: String,
        page: Int,
        langLevelId: String,
        teacherGender: String,
        days: String,
        dayPart: String
    ): Response<MainResponseDto<PagingMainDto<List<GetGroupByLangListDto>>>>

    suspend fun getGroupWFilter(
        id: String,
        page: Int,
    ): Response<MainResponseDto<PagingMainDto<List<GetGroupByLangListDto>>>>


    suspend fun getLessonDetail(
        courseId: String,
        type: String,
        lessonId: String,
    ): Response<MainResponseDto<GetLessonDetailDto>>

    suspend fun getLanguages(): Response<MainResponseDto<List<LanguageDto>>>

    /** ------------------------------------          Course         ------------------------------------------------------------------------  */

    suspend fun uploadImage(file: MultipartBody.Part): Response<MainResponseDto<String>>

    suspend fun getAllPodcastsCategory(id: String): Response<MainResponseDto<List<PodcastCategoryDtoItem>>>

    suspend fun getPodcastByCategory(
        page: Int, id: String
    ): Response<MainResponseDto<PagingMainDto<List<PodcastDataDto>>>>

    suspend fun getYouTobeVideo(
        page: Int,
        id: String
    ): Response<MainResponseDto<PagingMainDto<List<VideoDataDto>>>>

    suspend fun getLangLevelId(
        id: String
    ): Response<MainResponseDto<List<LabelDto>>>

    suspend fun getGroupById(
        id: String
    ): Response<MainResponseDto<GroupDetailDto>>

    suspend fun getPodcastInfo(path: String): Response<MainResponseDto<PodcastInfoDto>>

    suspend fun getHomeVideo(id: String): Response<MainResponseDto<HomeVideoDto>>

    suspend fun getNews(id: String): Response<MainResponseDto<NewsPaginationDto>>

    suspend fun getHistoryIncome(): Response<MainResponseDto<List<PaymentIncomeHistoryDto>>>

    suspend fun getHistoryOutcome(): Response<MainResponseDto<List<PaymentHistoryOutcomeDto>>>

    suspend fun pay(amount: Int, type: String): Response<MainResponseDto<String>>

    suspend fun updateProfile(updateProfile: UpdateProfile): Response<MainResponseDto<String>>

    suspend fun updateProfileFire(fireBaseDto: FireBaseModel): Response<MainResponseDto<String>>

    suspend fun updateActive(updateActiveDto: UpdateActiveDto): Response<MainResponseDto<String>>

    /**---------------------------Vocabulary-------------------------------**/
    suspend fun vocabularyM(
        id: String,
    ): Response<MainResponseDto<List<VocabularyDto>>>

//    suspend fun vocabulary(
//        id: String,
//    ): Response<MainResponseDto<PagingMainDto<List<VocabularyDto>>>>

    suspend fun savedV(saved: SavedVDto): Response<MainResponseDto<String>>

    /**---------------------------LessonVidio-------------------------------**/

    suspend fun lessonVidio(
        id: String,
    ): Response<MainResponseDto<List<LessonVidioDto>>>


    /**---------------------------Grammer-------------------------------**/
    suspend fun grammer(
        id: String,
    ): Response<MainResponseDto<List<GrammerDto>>>

    /**---------------------------Speaking-------------------------------**/
    suspend fun speaking(id: String): Response<MainResponseDto<List<SpeakingDto>>>

    /**---------------------------device-------------------------------**/
    suspend fun device(deviceDto: DeviceModel): Response<MainResponseDto<String>>

    /**---------------------------news-------------------------------**/
    suspend fun news(id: String): Response<MainResponseDto<NewsDto>>

    /**---------------------------Saved-------------------------------**/

    suspend fun getSaved(type: String): Response<MainResponseDto<List<GetSavedDto>>>

    suspend fun getSavedLesson(): Response<MainResponseDto<List<ObjectSavedLessonDto>>>
    suspend fun getSavedCount(): Response<MainResponseDto<List<SavedCountDto>>>


    /**---------------------------Saved-------------------------------**/

    /**-------------------------------HOMEWORK------------------------------*/

    suspend fun getHomeworkSpeaking(
        lessonId: String, type: String
    ): Response<MainResponseDto<List<GetHomeworkDto>>>

    /**-------------------------------HOMEWORK------------------------------*/
}