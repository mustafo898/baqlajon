package uz.rounded.baqlajon.presentation.ui.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getColor
import uz.rounded.baqlajon.databinding.ItemCategoryBinding

class HomeCategoryAdapter(private val context: Context) :
    RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder>() {

    private val list = mutableListOf<CategoryModel>()

    fun setList(data: List<CategoryModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    private var selected = 0

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryModel) {

            if (data.isClick) {
                binding.text.setTextColor(getColor(context, R.color.white))
                binding.card.setCardBackgroundColor(getColor(context, R.color.blue))
                binding.card.strokeColor = getColor(context, R.color.blue)
            } else {
                binding.text.setTextColor(getColor(context, R.color.grey2))
                binding.card.setCardBackgroundColor(getColor(context, R.color.white))
                binding.card.strokeColor = getColor(context, R.color.grey2)
            }

            itemView.setOnClickListener {
                list[selected].isClick = false
                selected = layoutPosition
                list[selected].isClick = true

                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}