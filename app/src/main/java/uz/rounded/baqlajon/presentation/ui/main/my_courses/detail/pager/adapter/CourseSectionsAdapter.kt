package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.databinding.ItemCourseSectionsBinding

class CourseSectionsAdapter : RecyclerView.Adapter<CourseSectionsAdapter.ViewHolder>() {

    private val list = mutableListOf<String>()

    fun setList(data: List<String>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    private var itemClickListener: (() -> Unit)? = null

    fun setItemClickListener(f: () -> Unit) {
        itemClickListener = f
    }

    inner class ViewHolder(private val binding: ItemCourseSectionsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            itemView.setOnClickListener {
                itemClickListener?.invoke()
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