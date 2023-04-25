package uz.rounded.baqlajon.presentation.ui.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getColor
import uz.rounded.baqlajon.databinding.ItemCategoryBinding

class HomeCategoryAdapter(
    private val context: Context,
    private val onItemClick: ((Int) -> Unit)
) :
    ListAdapter<CategoryModel, HomeCategoryAdapter.MyViewHolder>(TaskDiffCallBack()) {

    class TaskDiffCallBack : DiffUtil.ItemCallback<CategoryModel>() {
        override fun areItemsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean {
            return oldItem.text == newItem.text
        }

        override fun areContentsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean {
            return oldItem.text == newItem.text
        }
    }

    private var selected = 0

    inner class MyViewHolder(var itemBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(data: CategoryModel) {
            itemBinding.text.text = data.text

            itemView.setOnClickListener {
                selected = absoluteAdapterPosition
                onItemClick.invoke(absoluteAdapterPosition)
                notifyDataSetChanged()
            }
            if (selected == absoluteAdapterPosition) {
                itemBinding.card.setCardBackgroundColor(getColor(context, R.color.blue))
                itemBinding.text.setTextColor(getColor(context, R.color.white))
                itemBinding.card.strokeColor = getColor(context, R.color.blue)
            } else {
                itemBinding.card.setCardBackgroundColor(getColor(context, R.color.transparent))
                itemBinding.text.setTextColor(getColor(context, R.color.grey2))
                itemBinding.card.strokeColor = getColor(context, R.color.grey2)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCategoryBinding.inflate(
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