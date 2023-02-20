package uz.rounded.baqlajon.presentation.ui.start.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getColor
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.core.extensions.navigateWithArgs
import uz.rounded.baqlajon.databinding.FragmentRegistrationBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

    private var phoneLength = false
    private var phoneString = ""
    private var name = ""
    private var lastName = ""
    private var referalCode = ""
    private val type = 0
    private val password = ""


    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegistrationBinding = FragmentRegistrationBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

        setUpUi()
        validateFields()
    }

    private fun setUpUi() {

        binding.signUp.cardView.setCardBackgroundColor(
            getColor(
                requireContext(), R.color.button_disabled
            )
        )

        binding.signUp.cardView.isClickable = false

        binding.login.setOnClickListener {
            navigate(R.id.action_registrationFragment_to_loginFragment)
        }

        binding.signUp.cardView.setOnClickListener {
            if (validation()) {
                navigateWithArgs(
                    R.id.action_registrationFragment_to_smsVerifyFragment, bundleOf(
                        "NAME" to name,
                        "LASTNAME" to lastName,
                        "PHONE" to phoneString,
                        "REFERALCODE" to referalCode,
                        "TYPE" to type,
                    )
                )
            }
        }
    }

    private fun validation(): Boolean {
        return if (name.isNotEmpty() && lastName.isNotEmpty() && phoneLength) {
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
        binding.firstName.editText.addTextChangedListener {
            name = it.toString().trim()
            validation()
        }
        binding.lastName.editText.addTextChangedListener {
            lastName = it.toString().trim()
            validation()
        }
        binding.referralCode.editText.addTextChangedListener {
            referalCode = it.toString().trim()
            validation()
        }
    }
}