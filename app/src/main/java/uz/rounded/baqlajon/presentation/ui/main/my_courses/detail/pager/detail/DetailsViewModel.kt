package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.rounded.baqlajon.domain.model.main.course.CommentModel
import uz.rounded.baqlajon.domain.model.main.course.RequestCommentModel
import uz.rounded.baqlajon.domain.model.main.course.VideoModel
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.common.UIObjectState
import uz.rounded.baqlajon.presentation.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {
    private val _detail = MutableStateFlow(UIObjectState<VideoModel>())
    val detail = _detail.asStateFlow()

    fun getDetailVideo(id: String) {
        getDataObject({ mainRepository.getByIdVideo(id = id) }, _detail)
        Log.d("KJFNKJF", "getDetailVideo: viewmodel")
    }

    private val _finish = MutableStateFlow(UIObjectState<Boolean>())
    val finish = _finish.asStateFlow()

    fun finishVideo(id: String) {
        getDataObject({ mainRepository.finishVideo(id = id) }, _finish)
    }

    private val _create = MutableStateFlow(UIObjectState<CommentModel>())
    val create = _create.asStateFlow()

    fun createComment(id: String, requestCommentModel: RequestCommentModel) {
        getDataObject({
            mainRepository.createComment(
                id = id,
                requestCommentModel = requestCommentModel
            )
        }, _create)
    }
}