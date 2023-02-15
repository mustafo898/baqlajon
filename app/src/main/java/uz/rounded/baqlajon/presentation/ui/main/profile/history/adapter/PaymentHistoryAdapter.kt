package uz.rounded.baqlajon.presentation.ui.main.profile.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.databinding.ItemHomeListBinding
import uz.rounded.baqlajon.databinding.ItemPaymnetHistoryBinding

class PaymentHistoryAdapter(
    private val onItemClick: ((data: String) -> Unit)
) :
    ListAdapter<String, PaymentHistoryAdapter.MyViewHolder>(TaskDiffCallBack()) {

    class TaskDiffCallBack : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class MyViewHolder(var itemBinding: ItemPaymnetHistoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(data: String) {
            itemBinding.apply {

            }
            itemView.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemPaymnetHistoryBinding.inflate(
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