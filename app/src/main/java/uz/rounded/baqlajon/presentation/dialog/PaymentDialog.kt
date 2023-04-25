package uz.rounded.baqlajon.presentation.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.databinding.DialogPaymentBinding

class PaymentDialog(context: Context) : AlertDialog(context) {
    private var binding: DialogPaymentBinding = DialogPaymentBinding.inflate(layoutInflater)

//    private var dialog =
//        AlertDialog.Builder(context, R.style.SheetDialog).create()

    private var payment_type = "PAYME"

    init {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setView(binding.root)
        setCancelable(false)

        binding.back.setOnClickListener {
            dismiss()
        }

        paymentMethod()
        continuePayment()
    }

    private fun continuePayment() = binding.apply {
        card.cardView.setOnClickListener {
            dismiss()
        }
    }

    private fun paymentMethod() = binding.apply {
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

    private fun clearChecks() = binding.apply {
        paymeCheck.setImageResource(R.drawable.unchecked)
        clickCheck.setImageResource(R.drawable.unchecked)
    }

}