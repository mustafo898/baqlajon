package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.callback

import androidx.recyclerview.widget.DiffUtil
import uz.rounded.baqlajon.domain.model.main.course.ContentItemModel

class ContentDiffCallBack(
    private val oldData: List<ContentItemModel>,
    private val newData: List<ContentItemModel>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].id == newData[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }
}