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

    fun setCongratulations() {
        binding.type.text = getString(R.string.congratulations)
        binding.desc.text = getString(R.string.congratulations_text)
    }

    fun setError() {
        binding.type.text = getString(R.string.error)
        binding.desc.text = getString(R.string.error_text)
    }

    init {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setView(binding.root)
        setCancelable(true)

        binding.ok.cardView.setOnClickListener {
            dismiss()
        }
    }
}