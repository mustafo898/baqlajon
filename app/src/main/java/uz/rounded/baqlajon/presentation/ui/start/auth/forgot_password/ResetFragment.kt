package uz.rounded.baqlajon.presentation.ui.start.auth.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getColor
import uz.rounded.baqlajon.core.extensions.gone
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.core.extensions.visible
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.databinding.FragmentResetBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment
import javax.inject.Inject

class ResetFragment : BaseFragment<FragmentResetBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentResetBinding = FragmentResetBinding.inflate(inflater)

    private var password = ""
    private var confirmPassword = ""

    @Inject
    lateinit var shared: SharedPreference

    override fun created(view: View, savedInstanceState: Bundle?) {

        listener()
    }

    private fun validation(): Boolean {
        return if (password.matches(".*[A-Za-z@#\$%^_\\d].*".toRegex()) && password.length >= 8 && password == confirmPassword) {
            binding.next.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.main_blue
                )
            )
            binding.rule.gone()
            binding.next.cardView.isClickable = true
            binding.next.cardView.setOnClickListener {
                navigate(R.id.homeFragment)
            }
            true
        } else {
            binding.next.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.button_disabled
                )
            )
            binding.rule.visible()
            binding.rule.text = getString(R.string.password_rule)
            binding.next.cardView.isClickable = false
            false
        }
    }

    private fun listener() {
        binding.newPassword.addTextChangedListener {
            password = it.toString()
            shared.password = password
            validation()
        }

        binding.confirmPassword.addTextChangedListener {
            confirmPassword = it.toString()

            validation()
        }
    }
}