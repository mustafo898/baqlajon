package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video.viewholder

import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.item_content_photo.view.*
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.databinding.ItemContentPhotoBinding
import uz.rounded.baqlajon.domain.model.main.course.ContentItemModel
import uz.rounded.baqlajon.presentation.dialog.PhotoView

class PhotoViewHolder(
    val binding: ItemContentPhotoBinding,
    private val requestManager: RequestManager
) :
    BaseViewHolder(binding.root) {
    override fun bindData(data: ContentItemModel) {
        requestManager.load(binding.root.context.getString(R.string.base_url) + "public/uploads" + data.content)
            .into(itemView.ivContent)
        binding.ivContent.setOnClickListener {
            PhotoView(itemView.context, binding.ivContent.drawable).show()
        }
    }
}