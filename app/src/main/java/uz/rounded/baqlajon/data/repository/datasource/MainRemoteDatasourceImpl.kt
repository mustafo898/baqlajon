package uz.rounded.data.repository.datasource

import android.util.Log
import okhttp3.MultipartBody
import retrofit2.Response
import uz.rounded.data.local.dao.InputDao
import uz.rounded.data.local.database.model.DbDto
import uz.rounded.baqlajon.data.remote.MainService
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
import javax.inject.Inject

class MainRemoteDatasourceImpl @Inject constructor(
    private val mainService: MainService,
    private val courseService: CourseService,
    private val inputDao: InputDao
) : MainRemoteDatasource {

    override suspend fun getUserData(): Response<MainResponseDto<UserDto>> {
        return mainService.getUserData()
    }

    /** ------------------------------------          Forum         ------------------------------------------------------------------------  */

    override suspend fun getForumByPaging(
        page: Int,
    ): Response<MainResponseDto<PagingMainDto<List<DataPagingDto>>>> {
        return mainService.getForumByPaging(page)
    }

    override suspend fun getPodcastInfoDb(): List<DbDto> {
        return inputDao.getAllPodcasts()
    }

    override suspend fun addPodcastInfoDb(dto: DbDto) {
        inputDao.savePodcast(dto)
    }

    override suspend fun getForumByPagingCategory(
        page: Int,
        id: String
    ): Response<MainResponseDto<PagingMainDto<List<DataPagingDto>>>> {
        return mainService.getForumByPagingCategory(page = page, id = id)
    }

    override suspend fun searchForum(
        page: Int,
        search: String
    ): Response<MainResponseDto<PagingMainDto<List<DataPagingDto>>>> {
        return mainService.searchForum(page = page, search = search)
    }

    override suspend fun getForumById(id: String): Response<MainResponseDto<DataPagingDto>> {
        return mainService.getForumById(id)
    }

    override suspend fun getForumPostComments(id: String): Response<MainResponseDto<List<GetPostCommentsDto>>> {
        return mainService.getForumPostComments(id)
    }

    override suspend fun getSelectedPosts(type: String): Response<MainResponseDto<List<DataPagingDto>>> {
        return mainService.getSelectedPosts(type)
    }

//    override suspend fun getUnselectedPosts(): Response<MainResponseDto<List<DataPagingDto>>> {
//        return mainService.getUnselectedPosts()
//    }

    override suspend fun chooseAnswer(id: String): Response<MainResponseDto<Boolean>> {
        return mainService.chooseComment(id)
    }

    override suspend fun rateComment(rateCommentRequestDto: RateCommentRequestDto): Response<MainResponseDto<Any>> {
        return mainService.rateComment(rateCommentRequestDto)
    }

    override suspend fun ratePost(
        id: String,
    ): Response<MainResponseDto<String>> {
        return mainService.ratePost(id)
    }

    override suspend fun updateProfile(updateProfile: UpdateProfile): Response<MainResponseDto<String>> {
        return mainService.upDateProfile(updateProfile)
    }

    override suspend fun getHistoryIncome(): Response<MainResponseDto<List<PaymentIncomeHistoryDto>>> {
        return mainService.getHistoryIncome()
    }

    override suspend fun getHistoryOutcome(): Response<MainResponseDto<List<PaymentHistoryOutcomeDto>>> {
        return mainService.getHistoryOutcome()
    }

    override suspend fun pay(amount: Int, type: String): Response<MainResponseDto<String>> {
        return mainService.pay(amount = amount, type = type)
    }

    override suspend fun updateProfileFire(fireBaseDto: FireBaseModel): Response<MainResponseDto<String>> {
        return mainService.upDateProfileFire(fireBaseDto)
    }

    override suspend fun updateActive(updateActiveDto: UpdateActiveDto): Response<MainResponseDto<String>> {
        return mainService.updateActive(updateActiveDto)
    }

//    override suspend fun vocabulary(
//        id: String,
//        page: Int
//    ): Response<MainResponseDto<PagingMainDto<List<VocabularyDto>>>> {
//        return mainService.vocabulary(id, page)
//    }

    override suspend fun vocabularyM(id: String): Response<MainResponseDto<List<VocabularyDto>>> {
        return mainService.vocabularyList(id)
    }

    override suspend fun savedV(saved: SavedVDto): Response<MainResponseDto<String>> {
        return mainService.savedV(saved)
    }

    override suspend fun lessonVidio(id: String): Response<MainResponseDto<List<LessonVidioDto>>> {
        return mainService.lessonVidio(id)
    }

    //    override suspend fun vocabularyM(
//        id: String,
//        page: Int
//    ): Response<MainResponseDto<PagingMainDto<List<VocabularyDto>>>> {
//        return mainService.vocabularyList(id, page)
//    }
    override suspend fun grammer(id: String): Response<MainResponseDto<List<GrammerDto>>> {
        return mainService.grammer(id)
    }

    override suspend fun getFreeTime(id: String): Response<MainResponseDto<GetFreeTimeDto>> {
        return courseService.getFreeTime(id)
    }

    override suspend fun speaking(id: String): Response<MainResponseDto<List<SpeakingDto>>> {
        return mainService.speaking(id)
    }

    override suspend fun device(deviceDto: DeviceModel): Response<MainResponseDto<String>> {
        return mainService.device(deviceDto)
    }

    override suspend fun news(id: String): Response<MainResponseDto<NewsDto>> {
        return mainService.news(id)
    }

    override suspend fun getSaved(type: String): Response<MainResponseDto<List<GetSavedDto>>> {
        return mainService.getSaved(type)
    }

    override suspend fun getSavedLesson(): Response<MainResponseDto<List<ObjectSavedLessonDto>>> {
        return mainService.getSavedLesson()
    }

    override suspend fun getSavedCount(): Response<MainResponseDto<List<SavedCountDto>>> {
        return mainService.getSavedCount()
    }

    override suspend fun replyPost(replyToPostRequestDto: ReplyToPostRequestDto): Response<MainResponseDto<String>> {
        return mainService.replyToPost(replyToPostRequestDto)
    }

    override suspend fun complainToComment(complainDto: ComplainDto): Response<MainResponseDto<String>> {
        return mainService.complainToComment(complainDto)
    }

    override suspend fun createPostOnForum(
        createPostOnForumRequestDto: CreatePostOnForumRequestDto
    ): Response<MainResponseDto<String>> {
        return mainService.createPostOnForum(createPostOnForumRequestDto)
    }

    override suspend fun getForumCategory(): Response<MainResponseDto<List<AllCategoryDto>>> {
        return mainService.getForumCategory()
    }

    override suspend fun getFaq(): Response<MainResponseDto<List<GetFaqDto>>> {
        return mainService.getFaq()
    }

    override suspend fun getCourseFaq(id: String): Response<MainResponseDto<List<GetFaqDto>>> {
        return courseService.getFaq(id)
    }

    override suspend fun getAbout(): Response<MainResponseDto<AboutDto>> {
        return mainService.getAbout()
    }

    override suspend fun getContact(): Response<MainResponseDto<GetContactsDto>> {
        return mainService.getContact()
    }

    override suspend fun getPrivacy(): Response<MainResponseDto<AboutDto>> {
        return mainService.getPrivacy()
    }

    override suspend fun getDevices(): Response<MainResponseDto<List<GetDevicesDto>>> {
        return mainService.getDevices()
    }

    /** ------------------------------------          Forum         ------------------------------------------------------------------------  */

    /** ------------------------------------          Course         ------------------------------------------------------------------------  */

    override suspend fun getMyCourses(id: String): Response<MainResponseDto<List<GetMyCoursesDto>>> {
        return courseService.getMyCourses(id)
    }

    override suspend fun orderCourse(body: OrderCourseRequestDto): Response<MainResponseDto<Boolean>> {
        return courseService.orderCourse(body)
    }

    override suspend fun orderGroup(body: OrderGroupRequestDto): Response<MainResponseDto<Boolean>> {
        return courseService.orderGroup(body)
    }

    override suspend fun getSections(
        page: Int,
        id: String
    ): Response<MainResponseDto<PagingMainDto<List<SectionsDto>>>> {
        return courseService.getSections(id, page)
    }

    override suspend fun getPlatformZoomByID(id: String): Response<MainResponseDto<GetPlatformDetailsZoomDto>> {
        return courseService.getPlatformZoomByID(id)
    }

    override suspend fun updateSpeaking(updateSpeaking: UpdateSpeakingDto): Response<MainResponseDto<Boolean>> {
        return courseService.updateSpeaking(updateSpeaking)
    }

    override suspend fun updateHomeWork(updateHomeWorkDto: UpdateHomeWorkDto): Response<MainResponseDto<Boolean>> {
        return courseService.updateHomeWork(updateHomeWorkDto)
    }

    override suspend fun updateListening(updateListeningDto: UpdateListeningDto): Response<MainResponseDto<Boolean>> {
        return courseService.updateListening(updateListeningDto)
    }

    override suspend fun updateGrammar(updateGrammarDto: UpdateGrammarDto): Response<MainResponseDto<Boolean>> {
        return courseService.updateGrammar(updateGrammarDto)
    }

    override suspend fun updateVocabulary(updateVocabularyDto: UpdateVocabularyDto): Response<MainResponseDto<Boolean>> {
        return courseService.updateVocabulary(updateVocabularyDto)
    }

    override suspend fun getLessonsById(id: String): Response<MainResponseDto<List<GetLessonsByIdDto>>> {
        return courseService.getLessonsByID(id)
    }

    override suspend fun getIndividualById(id: String): Response<MainResponseDto<GetTeacherByIdDto>> {
        return courseService.getIndividualByID(id)
    }

    override suspend fun getListening(id: String): Response<MainResponseDto<List<GetListeningDto>>> {
        return courseService.getListening(id)
    }

    override suspend fun getMembers(id: String): Response<MainResponseDto<List<GetMembersDto>>> {
        return courseService.getMembers(id)
    }

    override suspend fun lessonStart(lessonStartRequestDto: LessonStartRequestDto): Response<MainResponseDto<String>> {
        return courseService.lessonsStart(lessonStartRequestDto)
    }

    override suspend fun lessonFinish(lessonFinishRequestDto: LessonFinishRequestDto): Response<MainResponseDto<Any>> {
        return courseService.lessonsFinish(lessonFinishRequestDto)
    }

    override suspend fun getGroupCourseById(
        id: String,
        page: Int,
        langLevelId: String,
        teacherGender: String,
        days: String,
        dayPart: String
    ): Response<MainResponseDto<PagingMainDto<List<GetGroupByLangListDto>>>> {
        return courseService.getGroupByLangId(
            id = id,
            page = page,
            levelId = langLevelId,
            teacherGender = teacherGender,
            dayPart = dayPart,
            days = days
        )
    }

    override suspend fun getGroupWFilter(
        id: String,
        page: Int
    ): Response<MainResponseDto<PagingMainDto<List<GetGroupByLangListDto>>>> {
        return courseService.getGroupWFilter(id, page)
    }

    override suspend fun getLessonDetail(
        courseId: String,
        type: String,
        lessonId: String
    ): Response<MainResponseDto<GetLessonDetailDto>> {
        return courseService.getLessonDetail(lessonId, type, courseId)
    }

    override suspend fun getLanguages(): Response<MainResponseDto<List<LanguageDto>>> {
        return courseService.getLanguages()
    }

    override suspend fun createCourseComment(createCourseCommentDto: CreateCourseCommentDto): Response<MainResponseDto<GetCourseCommentDto>> {
        return courseService.createCourseComments(createCourseCommentDto)
    }

    override suspend fun getCourseComments(id: String): Response<MainResponseDto<List<GetCourseCommentDto>>> {
        return courseService.getCourseComments(id)
    }

    override suspend fun getGroupComments(id: String): Response<MainResponseDto<List<GetCourseCommentDto>>> {
        return courseService.getGroupComments(id)
    }

    override suspend fun getCoursesByLang(
        page: Int,
        languageId: String
    ): Response<MainResponseDto<PagingMainDto<List<GetCoursesByLangDto>>>> {
        return courseService.getCoursesByLang(page = page, languageId = languageId)
    }

    override suspend fun getIndividualTeachers(
        page: Int,
        languageId: String
    ): Response<MainResponseDto<PagingMainDto<List<TeacherResponseDto>>>> {
        return courseService.getIndividualTeachers(page = page, languageId = languageId)
    }

    override suspend fun getLangLevelId(id: String): Response<MainResponseDto<List<LabelDto>>> {
        return courseService.getLangLevelId(id)
    }

    override suspend fun getGroupById(id: String): Response<MainResponseDto<GroupDetailDto>> {
        return courseService.getGroupByID(id)
    }

    /** ------------------------------------          Course         ------------------------------------------------------------------------  */

    override suspend fun uploadImage(file: MultipartBody.Part): Response<MainResponseDto<String>> {
        Log.d("EEEEEE", "uploadImage: $file")
        return mainService.uploadImage(file)
    }

    override suspend fun getAllPodcastsCategory(id: String): Response<MainResponseDto<List<PodcastCategoryDtoItem>>> {
        return mainService.getAllPodcasts(id)
    }

    override suspend fun getPodcastByCategory(
        page: Int,
        id: String
    ): Response<MainResponseDto<PagingMainDto<List<PodcastDataDto>>>> {
        Log.d("OOOOO", "getPodcastByCategory: $id")
        return mainService.getPodcastByCategory(page = page, id = id)
    }

    override suspend fun getYouTobeVideo(
        page: Int,
        id: String
    ): Response<MainResponseDto<PagingMainDto<List<VideoDataDto>>>> {
        return mainService.getYouTobeVideo(page = page, id = id)
    }

    override suspend fun getPodcastInfo(path: String): Response<MainResponseDto<PodcastInfoDto>> {
        return mainService.getPodcastInfo(path = path)
    }

    override suspend fun getHomeVideo(id: String): Response<MainResponseDto<HomeVideoDto>> {
        return mainService.getHomeVideo(id)
    }

    override suspend fun getNews(id: String): Response<MainResponseDto<NewsPaginationDto>> {
        return mainService.getNewsBlog(id)
    }

    override suspend fun getHomeworkSpeaking(
        lessonId: String,
        type: String
    ): Response<MainResponseDto<List<GetHomeworkDto>>> {
        return mainService.getHomeworkSpeaking(id = lessonId, type = type)
    }
}

