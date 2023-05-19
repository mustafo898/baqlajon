package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.domain.model.main.course.ContentItemModel
import uz.rounded.baqlajon.domain.model.main.course.VideoModel
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video.ContentOfTopic

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var listener: ((ContentItemModel) -> Unit)? = null

    abstract fun bindData(data: ContentItemModel)
}