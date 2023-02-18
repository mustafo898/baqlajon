package uz.rounded.baqlajon.presentation.ui.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.core.extensions.loadImage
import uz.rounded.baqlajon.databinding.ItemHomeListBinding
import uz.rounded.baqlajon.domain.model.main.course.GetCourseModel

class HomeCourseSecondAdapter(
    private val context: Context,
    private val onItemClick: ((data: String) -> Unit)
) :
    ListAdapter<GetCourseModel, HomeCourseSecondAdapter.MyViewHolder>(TaskDiffCallBack()) {

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
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(var binding: ItemHomeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: GetCourseModel) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemHomeListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.onBind(data)
    }
}