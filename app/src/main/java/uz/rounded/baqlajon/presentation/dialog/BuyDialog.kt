package uz.rounded.baqlajon.presentation.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getString
import uz.rounded.baqlajon.databinding.DialogBuyBinding

class BuyDialog(context: Context) : AlertDialog(context) {

    private var binding: DialogBuyBinding = DialogBuyBinding.inflate(layoutInflater)

    private var payment_type = "PAYME"

    private var itemClickListener: (() -> Unit)? = null

    fun setItemClickListener(f: () -> Unit) {
        itemClickListener = f
    }

    fun setCongratulations() {
        binding.animation.setAnimation(R.raw.congratulations)
        binding.type.text = getString(R.string.congratulations)
        binding.desc.text = getString(R.string.congratulations_text)
    }

    fun setError(error: String) {
        binding.animation.setAnimation(R.raw.not_money)
        binding.type.text = getString(R.string.error)
        binding.desc.text = error
    }

    init {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setView(binding.root)
        setCancelable(true)

        binding.ok.cardView.setOnClickListener {
            itemClickListener?.invoke()
            dismiss()
        }
    }
}