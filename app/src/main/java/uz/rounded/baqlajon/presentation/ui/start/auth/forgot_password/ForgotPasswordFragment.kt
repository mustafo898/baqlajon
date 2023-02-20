package uz.rounded.baqlajon.presentation.ui.start.auth.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getColor
import uz.rounded.baqlajon.core.extensions.navigateWithArgs
import uz.rounded.baqlajon.databinding.FragmentForgotPasswordBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentForgotPasswordBinding = FragmentForgotPasswordBinding.inflate(inflater)

    private var phoneString = ""
    private var type = 1
    private var phoneLength = false
    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.next.cardView.setCardBackgroundColor(
            getColor(
                requireContext(), R.color.button_disabled
            )
        )
        binding.next.cardView.isClickable = false

//        binding.signUp.setOnClickListener {
//            navigate(R.id.registrationFragment)
//        }

        validateFields()
        binding.next.cardView.setOnClickListener {
            if (validation()) {
                navigateWithArgs(
                    R.id.action_forgotPasswordFragment_to_smsVerifyFragment,
                    bundleOf("PHONE" to phoneString, "TYPE" to type)
                )
            }
        }
    }

    private fun validation(): Boolean {
        return if (phoneLength) {
            binding.next.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.main_blue
                )
            )
            binding.next.cardView.isClickable = true
            true
        } else {
            binding.next.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.button_disabled
                )
            )
            binding.next.cardView.isClickable = false
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