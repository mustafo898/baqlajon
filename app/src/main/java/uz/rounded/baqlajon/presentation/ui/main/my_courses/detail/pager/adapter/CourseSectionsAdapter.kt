package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.loadImage
import uz.rounded.baqlajon.databinding.ItemCourseSectionsBinding
import uz.rounded.baqlajon.domain.model.VideoModel

class CourseSectionsAdapter : RecyclerView.Adapter<CourseSectionsAdapter.ViewHolder>() {

    private val list = mutableListOf<VideoModel>()

    fun setList(data: List<VideoModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    private var itemClickListener: ((String) -> Unit)? = null

    fun setItemClickListener(f: (String) -> Unit) {
        itemClickListener = f
    }

    inner class ViewHolder(private val binding: ItemCourseSectionsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: VideoModel) {

            binding.typeImage.setImageResource(if (!data.isFree) R.drawable.lock else R.drawable.play_course)

            binding.image.loadImage(binding.root.context, data.imageUrl)
            binding.title.text = data.title
            binding.eye.text = data.viewCount.toString()
            binding.clock.text = (0).toString()

            itemView.setOnClickListener {
                itemClickListener?.invoke(data._id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCourseSectionsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}