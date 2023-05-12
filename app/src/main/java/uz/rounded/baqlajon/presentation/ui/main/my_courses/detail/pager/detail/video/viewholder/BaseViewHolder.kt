package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video.ContentOfTopic

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var listener: ((ContentOfTopic) -> Unit)? = null

    abstract fun bindData(data: ContentOfTopic)
}