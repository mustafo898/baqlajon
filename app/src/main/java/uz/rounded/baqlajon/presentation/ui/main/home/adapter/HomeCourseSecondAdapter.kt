package uz.rounded.baqlajon.presentation.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.databinding.ItemHomeListBinding

class HomeCourseSecondAdapter : RecyclerView.Adapter<HomeCourseSecondAdapter.ViewHolder>() {

    private val list = mutableListOf<String>()

    fun setList(data: List<String>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemHomeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemHomeListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}