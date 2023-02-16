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

    private var payment_type = "PAYME"

    init {
        dialog.setView(binding?.root)
        dialog.setCancelable(false)
        dialog.setOnDismissListener {
            binding = null
        }
        bind?.back?.setOnClickListener {
            dialog.dismiss()
        }
        paymentMethod()
        continuePayment()

    }

    private fun continuePayment() = bind?.apply {
        card.cardView.setOnClickListener {

        }
    }

    fun show() {
        dialog.show()
    }

    private fun paymentMethod() = bind?.apply {
        payme.setOnClickListener {
            clearChecks()
            paymeCheck.setImageResource(R.drawable.checked)
            payment_type = "PAYME"
        }
        click.setOnClickListener {
            clearChecks()
            clickCheck.setImageResource(R.drawable.checked)
            payment_type = "CLICK"

        }
    }

    private fun clearChecks() = bind?.apply {
        paymeCheck.setImageResource(R.drawable.unchecked)
        clickCheck.setImageResource(R.drawable.unchecked)
    }

}