package uz.rounded.data.repository

import android.util.Log
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import uz.rounded.data.common.ResponseHandler
import uz.rounded.data.common.createPager
import uz.rounded.data.local.database.model.DbDto
import uz.rounded.data.mapper.*
import uz.rounded.data.remote.dto.main.courses.comment.CreateCourseCommentDto
import uz.rounded.data.remote.dto.main.courses.lesson_finish.LessonFinishRequestDto
import uz.rounded.data.remote.dto.main.courses.lesson_start.LessonStartRequestDto
import uz.rounded.data.remote.dto.main.courses.lessons.*
import uz.rounded.data.remote.dto.main.courses.order.OrderCourseRequestDto
import uz.rounded.data.remote.dto.main.courses.order.OrderGroupRequestDto
import uz.rounded.data.remote.dto.main.forum.complain.ComplainDto
import uz.rounded.data.remote.dto.main.forum.create_post.CreatePostOnForumRequestDto
import uz.rounded.data.remote.dto.main.forum.rate.RateCommentRequestDto
import uz.rounded.data.remote.dto.main.forum.reply.ReplyToPostRequestDto
import uz.rounded.data.remote.dto.main.updateProfile.UpdateActiveDto
import uz.rounded.data.remote.dto.main.updateProfile.UpdateProfile
import uz.rounded.data.remote.dto.main.vocabulary.SavedVDto
import uz.rounded.data.repository.datasource.MainRemoteDatasource
import uz.rounded.domain.common.Resource
import uz.rounded.domain.model.AllCategoryModel
import uz.rounded.domain.model.UserProfile
import uz.rounded.domain.model.main.courses.comment.CreateCourseCommentModel
import uz.rounded.domain.model.main.courses.comment.GetCourseCommentModel
import uz.rounded.domain.model.main.courses.course_lang.GetCoursesByLangModel
import uz.rounded.domain.model.main.courses.free_time.GetFreeTimeModel
import uz.rounded.domain.model.main.courses.grammer.GrammerModel
import uz.rounded.domain.model.main.courses.group.GetGroupByLangListModel
import uz.rounded.domain.model.main.courses.group.GroupDetailModel
import uz.rounded.domain.model.main.courses.language.LanguageModel
import uz.rounded.domain.model.main.courses.lesson_finish.LessonFinishRequestModel
import uz.rounded.domain.model.main.courses.lesson_start.LessonStartRequestModel
import uz.rounded.domain.model.main.courses.lesson_vidio.LessonVidioModel
import uz.rounded.domain.model.main.courses.lessons.*
import uz.rounded.domain.model.main.courses.lessons.homework.GetHomeworkData
import uz.rounded.domain.model.main.courses.listening.GetListeningModel
import uz.rounded.domain.model.main.courses.mycourses.GetMyCoursesModel
import uz.rounded.domain.model.main.courses.order.OrderCourseRequestModel
import uz.rounded.domain.model.main.courses.order.OrderGroupRequestModel
import uz.rounded.domain.model.main.courses.platform.GetPlatformDetailsZoomModel
import uz.rounded.domain.model.main.courses.sections.LabelModel
import uz.rounded.domain.model.main.courses.sections.SectionsModel
import uz.rounded.domain.model.main.courses.speaking.SpeakingModel
import uz.rounded.domain.model.main.courses.teacher.GetTeacherByIdModel
import uz.rounded.domain.model.main.courses.teacher.TeacherResponseModel
import uz.rounded.domain.model.main.courses.vocabulary.SavedVModel
import uz.rounded.domain.model.main.courses.vocabulary.VocabularyModel
import uz.rounded.domain.model.main.device.DeviceModel
import uz.rounded.domain.model.main.firebase.FireBaseModel
import uz.rounded.domain.model.main.forum.comments.GetPostCommentsModel
import uz.rounded.domain.model.main.forum.complain.ComplainModel
import uz.rounded.domain.model.main.forum.create_post.CreatePostOnForumModel
import uz.rounded.domain.model.main.forum.paging.DataPagingModel
import uz.rounded.domain.model.main.forum.rate.RateCommentModel
import uz.rounded.domain.model.main.forum.reply.ReplyToPostModel
import uz.rounded.domain.model.main.home.news.NewsData
import uz.rounded.domain.model.main.home.video.VideoData
import uz.rounded.domain.model.main.news.NewsModel
import uz.rounded.domain.model.main.podcast.DbModel
import uz.rounded.domain.model.main.podcast.PodcastCategories
import uz.rounded.domain.model.main.podcast.podcastByCategory.PodcastData
import uz.rounded.domain.model.main.podcast.podcastByCategory.podcastInfo.PodcastInfo
import uz.rounded.domain.model.main.saved.GetSavedModel
import uz.rounded.domain.model.main.saved.SavedCountModel
import uz.rounded.domain.model.main.saved.saved_lesson.ObjectSavedLessonData
import uz.rounded.domain.model.main.settings.about.AboutModel
import uz.rounded.domain.model.main.settings.contact.GetContactsModel
import uz.rounded.domain.model.main.settings.device.GetDevicesModel
import uz.rounded.domain.model.main.settings.faq.GetFaqModel
import uz.rounded.domain.model.main.updateprofile.UpdateActiveModel
import uz.rounded.domain.model.main.updateprofile.UpdateProfileModel
import uz.rounded.domain.model.main.updateprofile.payment.history.PaymentHistoryOutcomeModel
import uz.rounded.domain.model.main.updateprofile.payment.history.PaymentIncomeHistoryModel
import uz.rounded.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remoteDatasource: MainRemoteDatasource
) : MainRepository, ResponseHandler() {

    override suspend fun getUserData(): Flow<Resource<UserProfile>> = loadResult({
        remoteDatasource.getUserData()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.toUser()))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    /** ------------------------------------          Forum         ------------------------------------------------------------------------  */

    override suspend fun createPostOnForum(createPostOnForumModel: CreatePostOnForumModel): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.createPostOnForum(
                CreatePostOnForumRequestDto(
                    createPostOnForumModel.img,
                    createPostOnForumModel.text,
                    createPostOnForumModel.categoryId
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getMyCourses(id: String): Flow<Resource<List<GetMyCoursesModel>>> = flow {
        val response = remoteDatasource.getMyCourses(id)
        emit(Resource.Loading())
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Resource.Success(it.data.map { t ->
                    t.toModel()
                }))
            }
        } else {
            emit(Resource.Error(response.message()))
        }
    }

    /** ------------------------------------          Forum         ------------------------------------------------------------------------  */

    override suspend fun getForumByPaging(): Flow<PagingData<DataPagingModel>> {
        return createPager { page ->
            val response = remoteDatasource.getForumByPaging(page)
            response.body()?.data?.data!!.map {
                it.toModel()
            }
        }.flow
    }

    override suspend fun getForumByPagingCategory(id: String): Flow<PagingData<DataPagingModel>> {
        return createPager { page ->
            val response = remoteDatasource.getForumByPagingCategory(page, id)
            response.body()?.data?.data!!.map {
                it.toModel()
            }
        }.flow
    }

    override suspend fun getPodcastInfoDb(): Resource<List<DbModel>> {
        return Resource.Success(remoteDatasource.getPodcastInfoDb()
            .map { it -> DbModel(_id = it._id, it.imgUrl, it.name, it.description) })
    }

    override suspend fun savePodcastInfo(dbModel: DbModel) {
        remoteDatasource.addPodcastInfoDb(
            DbDto(
                dbModel._id,
                dbModel.imgUrl,
                dbModel.name,
                dbModel.description
            )
        )
    }

    override suspend fun searchForum(search: String): Flow<PagingData<DataPagingModel>> {
        return createPager { page ->
            val response = remoteDatasource.searchForum(page, search)
            response.body()?.data?.data!!.map {
                it.toModel()
            }
        }.flow
    }

    override suspend fun getForumById(id: String): Flow<Resource<DataPagingModel>> =
        loadResult({
            remoteDatasource.getForumById(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })


    override suspend fun getFreeTime(id: String): Flow<Resource<GetFreeTimeModel>> =
        loadResult({
            remoteDatasource.getFreeTime(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getSelectedPosts(type: String): Flow<Resource<List<DataPagingModel>>> =
        loadResult({
            remoteDatasource.getSelectedPosts(type)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

//    override suspend fun getUnselectedPosts(): Flow<Resource<List<DataPagingModel>>> =
//        loadResult({
//            remoteDatasource.getUnselectedPosts()
//        }, { data, flow ->
//            try {
//                flow.emit(Resource.Success(data.map { it.toModel() }))
//            } catch (e: Exception) {
//                flow.emit(Resource.Error(e.message.toString()))
//            }
//        })

    override suspend fun chooseAnswer(id: String): Flow<Resource<Boolean>> =
        loadResult({
            remoteDatasource.chooseAnswer(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getForumPostComments(id: String): Flow<Resource<List<GetPostCommentsModel>>> =
        loadResult({
            remoteDatasource.getForumPostComments(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map {
                    it.toModel()
                }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun rateComment(rateCommentModel: RateCommentModel): Flow<Resource<Any>> =
        loadResult({
            remoteDatasource.rateComment(
                RateCommentRequestDto(
                    rateCommentModel.like,
                    rateCommentModel.dislike,
                    rateCommentModel.replyId,
                    rateCommentModel.postId
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun ratePost(
        id: String
    ): Flow<Resource<String>> = loadResult({
        remoteDatasource.ratePost(id)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun replyToPost(replyToPostModel: ReplyToPostModel): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.replyPost(
                ReplyToPostRequestDto(
                    replyToPostModel.comment,
                    replyToPostModel.postId,
                    replyToPostModel.createdDate
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun complainToComment(complainModel: ComplainModel): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.complainToComment(
                ComplainDto(
                    complainModel.postId,
                    complainModel.replyId
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getForumCategory(): Flow<Resource<List<AllCategoryModel>>> = loadResult({
        remoteDatasource.getForumCategory()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map {
                it.toModel()
            }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getFaq(): Flow<Resource<List<GetFaqModel>>> = loadResult({
        remoteDatasource.getFaq()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map {
                it.toModel()
            }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getCourseFaq(id: String): Flow<Resource<List<GetFaqModel>>> = loadResult({
        remoteDatasource.getCourseFaq(id)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map {
                it.toModel()
            }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getAbout(): Flow<Resource<AboutModel>> = loadResult({
        remoteDatasource.getAbout()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.toModel()))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getContact(): Flow<Resource<GetContactsModel>> = loadResult({
        remoteDatasource.getContact()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.toModel()))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getPrivacy(): Flow<Resource<AboutModel>> = loadResult({
        remoteDatasource.getPrivacy()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.toModel()))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getDevices(): Flow<Resource<List<GetDevicesModel>>> = loadResult({
        remoteDatasource.getDevices()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    /** ------------------------------------          Forum         ------------------------------------------------------------------------  */

    override suspend fun savedV(saved: SavedVModel): Flow<Resource<String>> = loadResult({
        remoteDatasource.savedV(
            SavedVDto(
                saved.type,
                saved.itemId
            )
        )
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getVocabularyM(
        id: String
    ): Flow<Resource<List<VocabularyModel>>> = loadResult({
        remoteDatasource.vocabularyM(id)
    }, { data, flow ->
        try {

            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun createCourseComment(createCourseCommentDto: CreateCourseCommentModel): Flow<Resource<GetCourseCommentModel>> =
        loadResult({
            remoteDatasource.createCourseComment(
                CreateCourseCommentDto(
                    createCourseCommentDto.courseId,
                    createCourseCommentDto.comment,
                    createCourseCommentDto.createdDate
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getCourseComments(id: String): Flow<Resource<List<GetCourseCommentModel>>> =
        loadResult({
            remoteDatasource.getCourseComments(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map {
                    it.toModel()
                }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getGroupComments(id: String): Flow<Resource<List<GetCourseCommentModel>>> =
        loadResult({
            remoteDatasource.getGroupComments(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map {
                    it.toModel()
                }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getLessonsById(id: String): Flow<Resource<List<GetLessonsByIdModel>>> =
        loadResult({
            remoteDatasource.getLessonsById(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getListening(id: String): Flow<Resource<List<GetListeningModel>>> =
        loadResult({
            remoteDatasource.getListening(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getMembers(id: String): Flow<Resource<List<GetMembersModel>>> =
        loadResult({
            remoteDatasource.getMembers(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun lessonVidio(id: String): Flow<Resource<List<LessonVidioModel>>> =
        loadResult({
            remoteDatasource.lessonVidio(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun grammer(id: String): Flow<Resource<List<GrammerModel>>> = loadResult({
        remoteDatasource.grammer(id)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun speaking(id: String): Flow<Resource<List<SpeakingModel>>> =
        loadResult({
            remoteDatasource.speaking(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun device(deviceModel: DeviceModel): Flow<Resource<String>> = loadResult({
        remoteDatasource.device(
            DeviceModel(
                deviceModel.device,
                deviceModel.ip_address
            )
        )
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }

    })

    override suspend fun news(id: String): Flow<Resource<NewsModel>> = loadResult({
        remoteDatasource.news(id)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.toModel()))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }

    })

    /***---------------------Saved--------------------***/

    override suspend fun getSaved(type: String):
            Flow<Resource<List<GetSavedModel>>> = loadResult({
        remoteDatasource.getSaved(type = type)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data = data.map { it.toModel() }))
            Log.d("GGGGGGGG", "implementation: $data")
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getSavedLesson(): Flow<Resource<List<ObjectSavedLessonData>>> =
        loadResult({
            remoteDatasource.getSavedLesson()
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data = data.map { it.toData() }))
                Log.d("GGGGGGGG", "implementation: $data")
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getSavedCount(): Flow<Resource<List<SavedCountModel>>> = loadResult({
        remoteDatasource.getSavedCount()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data = data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    /***---------------------Saved--------------------***/


    override suspend fun lessonFinish(lessonFinishRequestModel: LessonFinishRequestModel): Flow<Resource<Any>> =
        loadResult({
            remoteDatasource.lessonFinish(
                LessonFinishRequestDto(
                    lessonFinishRequestModel.courseId,
                    lessonFinishRequestModel.grammarScore,
                    lessonFinishRequestModel.homeworkScore,
                    lessonFinishRequestModel.lessonId,
                    lessonFinishRequestModel.listeningScore,
                    lessonFinishRequestModel.score,
                    lessonFinishRequestModel.speakingScore,
                    lessonFinishRequestModel.type
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun updateSpeaking(updateSpeaking: UpdateSpeakingModel): Flow<Resource<Boolean>> =
        loadResult({
            remoteDatasource.updateSpeaking(
                UpdateSpeakingDto(
                    updateSpeaking.courseId,
                    updateSpeaking.lessonId,
                    updateSpeaking.speaking,
                    updateSpeaking.type
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun updateHomeWork(updateHomeWorkModel: UpdateHomeWorkModel): Flow<Resource<Boolean>> =
        loadResult({
            remoteDatasource.updateHomeWork(
                UpdateHomeWorkDto(
                    updateHomeWorkModel.courseId,
                    updateHomeWorkModel.lessonId,
                    updateHomeWorkModel.homework,
                    updateHomeWorkModel.type
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })


    override suspend fun updateListening(updateListeningDto: UpdateListeningModel): Flow<Resource<Boolean>> =
        loadResult({
            remoteDatasource.updateListening(
                UpdateListeningDto(
                    updateListeningDto.courseId,
                    updateListeningDto.lessonId,
                    updateListeningDto.listening,
                    updateListeningDto.type
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun updateGrammar(updateGrammarDto: UpdateGrammarModel): Flow<Resource<Boolean>> =
        loadResult({
            remoteDatasource.updateGrammar(
                UpdateGrammarDto(
                    updateGrammarDto.courseId,
                    updateGrammarDto.lessonId,
                    updateGrammarDto.grammar,
                    updateGrammarDto.type
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun updateVocabulary(updateVocabularyDto: UpdateVocabularyModel): Flow<Resource<Boolean>> =
        loadResult({
            remoteDatasource.updateVocabulary(
                UpdateVocabularyDto(
                    updateVocabularyDto.courseId,
                    updateVocabularyDto.lessonId,
                    updateVocabularyDto.vocabulary,
                    updateVocabularyDto.type
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getLessonDetail(
        courseId: String,
        type: String,
        lessonId: String
    ): Flow<Resource<GetLessonDetailModel>> =
        loadResult({
            remoteDatasource.getLessonDetail(courseId, type, lessonId)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun lessonStart(lessonStartRequestModel: LessonStartRequestModel): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.lessonStart(
                LessonStartRequestDto(
                    lessonStartRequestModel.courseId,
                    lessonStartRequestModel.lessonId,
                    lessonStartRequestModel.type
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getGroupCourseById(
        id: String, langLevelId: String, teacherGender: String, days: String, dayPart: String
    ): Flow<PagingData<GetGroupByLangListModel>> {
        return createPager { page ->
            val response = remoteDatasource.getGroupCourseById(
                id, page, langLevelId, teacherGender, days, dayPart
            )
            response.body()?.data?.data!!.map {
                it.toModel()
            }
        }.flow
    }

    override suspend fun getGroupWFilter(id: String): Flow<PagingData<GetGroupByLangListModel>> {
        return createPager { page ->
            val response = remoteDatasource.getGroupWFilter(
                id, page
            )
            response.body()?.data?.data!!.map {
                it.toModel()
            }
        }.flow
    }

    override suspend fun getCoursesByLang(
        languageId: String
    ): Flow<PagingData<GetCoursesByLangModel>> {
        return createPager { page ->
            val response = remoteDatasource.getCoursesByLang(page, languageId)
            response.body()?.data?.data!!.map {
                it.toModel()
            }
        }.flow
    }

    override suspend fun getIndividualById(id: String): Flow<Resource<GetTeacherByIdModel>> =
        loadResult({
            remoteDatasource.getIndividualById(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })


    override suspend fun getIndividualTeachers(languageId: String): Flow<PagingData<TeacherResponseModel>> {
        return createPager { page ->
            val response = remoteDatasource.getIndividualTeachers(page, languageId)
            response.body()?.data?.data!!.map {
                it.toModel()
            }
        }.flow
    }

    override suspend fun getLangLevelId(languageId: String): Flow<Resource<List<LabelModel>>> =
        loadResult({
            remoteDatasource.getLangLevelId(languageId)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getGroupById(id: String): Flow<Resource<GroupDetailModel>> =
        loadResult({
            remoteDatasource.getGroupById(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun orderCourse(body: OrderCourseRequestModel): Flow<Resource<Boolean>> =
        loadResult({
            remoteDatasource.orderCourse(
                OrderCourseRequestDto(
                    body.courseId, body.type
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun orderGroup(body: OrderGroupRequestModel): Flow<Resource<Boolean>> =
        loadResult({
            remoteDatasource.orderGroup(
                OrderGroupRequestDto(
                    body.courseId, body.groupId, body.type
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getSections(
        id: String
    ): Flow<PagingData<SectionsModel>> {
        return createPager { page ->
            val response = remoteDatasource.getSections(page, id)
            response.body()?.data?.data!!.map {
                it.toModel()
            }
        }.flow
    }

    override suspend fun getPlatformZoomByID(id: String): Flow<Resource<GetPlatformDetailsZoomModel>> =
        loadResult({
            remoteDatasource.getPlatformZoomByID(id)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getLanguages(): Flow<Resource<List<LanguageModel>>> = loadResult({
        remoteDatasource.getLanguages()
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    /** ------------------------------------          Course         ------------------------------------------------------------------------  */

    /** ------------------------------------          PAYMENT         ------------------------------------------------------------------------  */
    override suspend fun getHistoryIncome(): Flow<Resource<List<PaymentIncomeHistoryModel>>> =
        loadResult({
            remoteDatasource.getHistoryIncome()
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getHistoryOutcome(): Flow<Resource<List<PaymentHistoryOutcomeModel>>> =
        loadResult({
            remoteDatasource.getHistoryOutcome()
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun pay(amount: Int, type: String): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.pay(amount = amount, type = type)
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    /** ------------------------------------          PAYMENT         ------------------------------------------------------------------------  */

    /** ------------------------------------          Upload         ------------------------------------------------------------------------  */

    override suspend fun uploadImage(file: MultipartBody.Part): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.uploadImage(file)
        }, { data, flow ->
            try {
                Log.d("EEEEEE", "uploadImage: $file")
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                Log.d("EEEEEE", "error: $e")
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    /** ------------------------------------          Upload         ------------------------------------------------------------------------  */

    override suspend fun updateProfile(updateProfileModel: UpdateProfileModel): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.updateProfile(
                UpdateProfile(
                    updateProfileModel.imageUrl,
                    updateProfileModel.firstName,
                    updateProfileModel.lastName,
                    updateProfileModel.phoneNumber,
                    updateProfileModel.birthDate,
                    updateProfileModel.address,
                    updateProfileModel.password,
                    /*updateProfileModel.email,*/
                    updateProfileModel.gender,
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun updateProfileFire(fireBaseModel: FireBaseModel): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.updateProfileFire(
                FireBaseModel(
                    fireBaseModel.firebase_token
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })


    override suspend fun updateActive(updateActiveModel: UpdateActiveModel): Flow<Resource<String>> =
        loadResult({
            remoteDatasource.updateActive(
                UpdateActiveDto(
                    updateActiveModel.isActive,
                    updateActiveModel.lastOnline
                )
            )
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getPodcastInfo(path: String): Flow<Resource<PodcastInfo>> =
        loadResult({
            remoteDatasource.getPodcastInfo(path)
        }, { data, flow ->
            try {
                remoteDatasource.addPodcastInfoDb(
                    DbDto(
                        data._id,
                        data.imgUrl,
                        data.name,
                        data.description
                    )
                )
                flow.emit(Resource.Success(data.toPodcastInfo()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getAllPodcastsCategory(id: String): Flow<Resource<List<PodcastCategories>>> =
        loadResult({
            remoteDatasource.getAllPodcastsCategory(id)
        }, { data, flow ->
            try {
                data.forEach {
                    Log.d("MAIN", "CATEGORY -> : ${it.category}\n")
                    it.podcasts.forEach { podcast ->
                        Log.d("MAIN", "PODCAST -> : ${podcast.name}\n")

                    }
                }

                flow.emit(Resource.Success(data.map {
                    it.toPodcastCategory()
                }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getHomeVideo(id: String): Flow<Resource<List<VideoData>>> = loadResult({
        remoteDatasource.getHomeVideo(id)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.data.map {
                it.toVideo()
            }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getNews(id: String): Flow<Resource<List<NewsData>>> = loadResult({
        remoteDatasource.getNews(id)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.data.map {
                it.toNews()
            }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getPodcastByCategory(id: String): Flow<PagingData<PodcastData>> {
        Log.d("OOOOO", "use case: $id")

        return createPager { page ->
            val response = remoteDatasource.getPodcastByCategory(page = page, id = id)
            response.body()?.data?.data!!.map {
                it.toPodcastData()
            }
        }.flow

    }

    override suspend fun getYouTobeVideo(id: String): Flow<PagingData<VideoData>> {
        return createPager { page ->
            val response = remoteDatasource.getYouTobeVideo(page = page, id)
            response.body()?.data?.data!!.map { it.toVideo() }
        }.flow
    }

    /**--------------------------------------HOMEWORK-------------------------------------**/
    override suspend fun getHomework(
        lessonId: String,
        type: String
    ): Flow<Resource<List<GetHomeworkData>>> = loadResult({
        remoteDatasource.getHomeworkSpeaking(lessonId = lessonId, type = type)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toData() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })
    /**--------------------------------------HOMEWORK-------------------------------------**/
}