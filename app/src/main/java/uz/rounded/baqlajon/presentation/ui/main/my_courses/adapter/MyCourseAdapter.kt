package uz.rounded.baqlajon.presentation.ui.main.my_courses.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.core.extensions.loadImage
import uz.rounded.baqlajon.databinding.ItemMyCoursesBinding
import uz.rounded.baqlajon.domain.model.GetMyCourseModel

class MyCourseAdapter(
    private val context: Context,
    private val onItemClick: ((data: String) -> Unit)
) :
    ListAdapter<GetMyCourseModel, MyCourseAdapter.MyViewHolder>(TaskDiffCallBack()) {

    class TaskDiffCallBack : DiffUtil.ItemCallback<GetMyCourseModel>() {
        override fun areItemsTheSame(
            oldItem: GetMyCourseModel,
            newItem: GetMyCourseModel
        ): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: GetMyCourseModel,
            newItem: GetMyCourseModel
        ): Boolean {
            return oldItem._id == newItem._id
        }
    }

    inner class MyViewHolder(var binding: ItemMyCoursesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(data: GetMyCourseModel) {

            binding.progressText.text = "${data.completedCount}/${data.course.videoCount}"
            binding.image.loadImage(context, data.course.image)

            binding.progressBar.max = data.course.viewCount
            binding.progressBar.progress = data.completedCount

            binding.eye.text = data.course.viewCount.toString()
            binding.rating.text = data.course.rating.toString()

            itemView.setOnClickListener {
                onItemClick.invoke(data._id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemMyCoursesBinding.inflate(
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