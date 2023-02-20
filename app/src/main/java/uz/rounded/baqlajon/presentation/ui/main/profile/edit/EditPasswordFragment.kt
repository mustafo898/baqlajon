package uz.rounded.baqlajon.presentation.ui.main.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getColor
import uz.rounded.baqlajon.core.extensions.gone
import uz.rounded.baqlajon.core.extensions.navigateWithArgs
import uz.rounded.baqlajon.core.extensions.visible
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.databinding.FragmentEditPasswordBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import javax.inject.Inject

@AndroidEntryPoint
class EditPasswordFragment : BaseFragment<FragmentEditPasswordBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentEditPasswordBinding.inflate(inflater)

    @Inject
    lateinit var sharedPreference: SharedPreference
    private var password = ""
    private var confirmPassword = ""

    override fun created(view: View, savedInstanceState: Bundle?) {
        hideMainProgress()
        listener()
    }

    private fun validation(): Boolean {
        return if (password.matches(".*[A-Za-z\\d].*".toRegex()) && password.length >= 8 && password == confirmPassword) {
            binding.signUp.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.main_blue
                )
            )
            binding.rule.gone()
            binding.signUp.cardView.isClickable = true
            binding.signUp.cardView.setOnClickListener {
                navigateWithArgs(
                    R.id.editProfileFragment,
                    bundleOf("PASSWORD" to password, "TYPE" to 1)
                )
            }
            true
        } else {
            binding.signUp.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.button_disabled
                )
            )
            binding.rule.visible()
            binding.rule.text = getString(R.string.password_rule)
            binding.signUp.cardView.isClickable = false
            false
        }
    }

    private fun listener() {
        binding.newPassword.editText.addTextChangedListener {
            password = it.toString()
            sharedPreference.user.password = password
            validation()
        }

        binding.confirmPassword.editText.addTextChangedListener {
            confirmPassword = it.toString()
            validation()
        }
    }
}