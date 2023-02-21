package uz.rounded.baqlajon.presentation.ui.main.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getColor
import uz.rounded.baqlajon.core.extensions.navigateWithArgs
import uz.rounded.baqlajon.databinding.FragmentEditPhoneBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment

class EditPhoneFragment : BaseFragment<FragmentEditPhoneBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentEditPhoneBinding.inflate(inflater)

    private var phoneLength = false
    private var phoneString = ""

    override fun created(view: View, savedInstanceState: Bundle?) {
        hideMainProgress()

        validateFields()

        binding.signUp.cardView.setOnClickListener {
            if (validation()) {
                navigateWithArgs(
                    R.id.action_editPhoneFragment_to_smsVerifyFragment2, bundleOf(
                        "PHONE" to phoneString,
                        "TYPE" to 4,
                    )
                )
            }
        }
    }

    private fun validation(): Boolean {
        return if (phoneLength) {
            binding.signUp.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.main_blue
                )
            )
            binding.signUp.cardView.isClickable = true
            true
        } else {
            binding.signUp.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.button_disabled
                )
            )
            binding.signUp.cardView.isClickable = false
            false
        }
    }

    private fun validateFields() {
        binding.phoneNumber.addTextChangedListener {
            phoneLength = it.toString().length == 17
            validation()
            phoneString = it.toString().trim()
        }

    }
}