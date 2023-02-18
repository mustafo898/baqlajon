package uz.rounded.baqlajon.presentation.ui.main.balance.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.loadImage
import uz.rounded.baqlajon.databinding.ItemShopBinding
import uz.rounded.baqlajon.domain.model.main.gift.GetGiftModel

class ShopAdapter(
    private val context: Context,
    private val onItemClick: ((String) -> Unit)
) :
    ListAdapter<GetGiftModel, ShopAdapter.MyViewHolder>(TaskDiffCallBack()) {

    class TaskDiffCallBack : DiffUtil.ItemCallback<GetGiftModel>() {
        override fun areItemsTheSame(
            oldItem: GetGiftModel,
            newItem: GetGiftModel
        ): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: GetGiftModel,
            newItem: GetGiftModel
        ): Boolean {
            return oldItem._id == newItem._id
        }
    }

    inner class MyViewHolder(var itemBinding: ItemShopBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(data: GetGiftModel) {

            itemBinding.apply {
                name.text = data.name
                coins.text = context.getString(R.string.coins, data.coin.toString())
                image.loadImage(context, data.image)
            }

            itemBinding.buy.setOnClickListener {
                onItemClick.invoke(data._id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemShopBinding.inflate(
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