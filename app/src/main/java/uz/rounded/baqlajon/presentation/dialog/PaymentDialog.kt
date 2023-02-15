package uz.rounded.baqlajon.presentation.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.databinding.DialogPaymentBinding

class PaymentDialog(
    val context: Context
) {
    private var binding: DialogPaymentBinding? = DialogPaymentBinding.inflate(
        LayoutInflater.from(context), null, false
    )
    private var dialog =
        AlertDialog.Builder(context, R.style.SheetDialog).create()

    private val bind get() = binding

    init {
        dialog.setView(binding?.root)
        dialog.setCancelable(false)
        dialog.setOnDismissListener {
            binding = null
        }
        bind?.back?.setOnClickListener {
            dialog.dismiss()
        }

    }

    fun show() {
        dialog.show()
    }

}