package uz.rounded.baqlajon.presentation.ui.main.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.core.extensions.loadImage
import uz.rounded.baqlajon.databinding.ItemHomeListBinding
import uz.rounded.baqlajon.domain.model.main.course.GetCourseModel

class SearchAllCourseAdapter(
    private val context: Context,
    private val onItemClick: ((data: String) -> Unit)

    ) :
    PagingDataAdapter<GetCourseModel, SearchAllCourseAdapter.MyViewHolder>(TaskDiffCallBack()) {

    class TaskDiffCallBack : DiffUtil.ItemCallback<GetCourseModel>() {
        override fun areItemsTheSame(
            oldItem: GetCourseModel,
            newItem: GetCourseModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetCourseModel,
            newItem: GetCourseModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    private var itemLikeClickListener: ((replyId: String, like: Boolean, dislike: Boolean) -> Unit)? =
        null

    fun setItemLikeClickListener(f: (replyId: String, like: Boolean, dislike: Boolean) -> Unit) {
        itemLikeClickListener = f
    }

    private var itemClickListener: ((studentId: String) -> Unit)? = null

    fun setItemClickListener(f: (replyId: String) -> Unit) {
        itemClickListener = f
    }

    inner class MyViewHolder(var binding: ItemHomeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "CheckResult")
        fun bind(data: GetCourseModel) {
            Log.d("RRRRR", "observe: $data")
            //set data

            binding.image.loadImage(context, data.image)

//            binding.type.text = data.title
            binding.title.text = data.description

            binding.eye.text = data.viewCount.toString()
            binding.rating.text = data.rating.toString()

            itemView.setOnClickListener {
                onItemClick.invoke(data.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemHomeListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        data?.let { holder.bind(it) }
    }
}