package uz.rounded.baqlajon.presentation.ui.main.my_courses.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.databinding.ItemHomeListBinding
import uz.rounded.baqlajon.databinding.ItemMyCoursesBinding

class MyCourseAdapter : RecyclerView.Adapter<MyCourseAdapter.ViewHolder>() {

    private val list = mutableListOf<String>()

    fun setList(data: List<String>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMyCoursesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemMyCoursesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}