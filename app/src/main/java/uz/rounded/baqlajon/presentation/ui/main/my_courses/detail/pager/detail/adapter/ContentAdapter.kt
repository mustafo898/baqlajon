package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.utils.Constants
import uz.rounded.baqlajon.domain.model.main.course.ContentItemModel
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.callback.ContentAdapterCallBack
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.callback.ContentDiffCallBack
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video.viewholder.*

class ContentAdapter(
    context: Context
) : RecyclerView.Adapter<BaseViewHolder>() {
    var data: List<ContentItemModel> = ArrayList()

    var callBack: ContentAdapterCallBack? = null

    private var requestManager: RequestManager = Glide.with(context).setDefaultRequestOptions(
        RequestOptions.placeholderOf(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        Log.d("FKJDJFKS", "onCreateViewHolder: $viewType")
        return when (viewType) {
            Constants.Type.VIDEO -> {
                VideoViewHolder(
                    DataBindingUtil.inflate(
                        inflater, R.layout.item_content_video, parent, false
                    )
                )
            }
            Constants.Type.DESCRIPTION -> {
                TextViewHolder(
                    DataBindingUtil.inflate(
                        inflater, R.layout.item_content_text, parent, false
                    )
                )
            }
            Constants.Type.PHOTO -> {
                PhotoViewHolder(
                    DataBindingUtil.inflate(
                        inflater, R.layout.item_content_photo, parent, false
                    ), requestManager
                )
            }
//            Constants.Type.AUDIO -> {
//                AudioViewHolder(
//                    DataBindingUtil.inflate(
//                        inflater,
//                        R.layout.item_content_audio,
//                        parent,
//                        false
//                    )
//                )
//            }
            else -> {
                FileViewHolder(
                    DataBindingUtil.inflate(
                        inflater, R.layout.item_content_file, parent, false
                    )
                )
            }
        }
    }

    fun swapData(data: List<ContentItemModel>) {
        val oldData = this.data
        val newData = data
        val diffUtil = DiffUtil.calculateDiff(ContentDiffCallBack(oldData, newData))
        this.data = data
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindData(data[position])
        Log.d("LFDSNLF", "onBindViewHolder: $$$")
        holder.listener = {
            Log.d("LFDSNLF", "onBindViewHolder: $it")
            callBack?.onItemClick(it)
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = data[position].type

}