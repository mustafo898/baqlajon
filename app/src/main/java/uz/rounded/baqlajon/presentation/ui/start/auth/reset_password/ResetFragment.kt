package uz.rounded.baqlajon.presentation.ui.start.auth.reset_password

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getColor
import uz.rounded.baqlajon.core.extensions.gone
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.core.extensions.visible
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.databinding.FragmentResetBinding
import uz.rounded.baqlajon.domain.model.auth.password.ForgotPasswordModel
import uz.rounded.baqlajon.domain.model.auth.register.RegisterModel
import uz.rounded.baqlajon.presentation.MainActivity
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import uz.rounded.baqlajon.presentation.ui.start.auth.sms.SmsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ResetFragment : BaseFragment<FragmentResetBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentResetBinding = FragmentResetBinding.inflate(inflater)

    private var password = ""
    private var confirmPassword = ""
    private var name = ""
    private var lastname = ""
    private var phone = ""
    private var image = ""
    private var referralCode = ""
    private var type = 0

    @Inject
    lateinit var shared: SharedPreference

    private val viewModel: SmsViewModel by viewModels()
    private val resetViewModel: ResetPasswordViewModel by viewModels()

    override fun created(view: View, savedInstanceState: Bundle?) {
        bundle()
        listener()
    }

    private fun bundle() {
        val bundle: Bundle? = this.arguments
        bundle?.let {
            name = it.getString("NAME", "")
            lastname = it.getString("LASTNAME", "")
            phone = it.getString("PHONE", "")
            type = it.getInt("TYPE", 0)
            image = it.getString("IMAGE", "")
            referralCode = it.getString("REFERALCODE", "")

            Log.d("sdlsjdfkh", "bundle: password $referralCode")
        }
    }

    private fun registerUser() {
        viewModel.register(
            RegisterModel(
                firstName = name,
                lastName = lastname,
                image = image,
                promocode = referralCode,
                password = password,
                phoneNumber = phone
            )
        )

        lifecycleScope.launchWhenStarted {
            viewModel.registration.collectLatest { k ->
                k.data?.let {
                    hideStartProgress()
                    shared.user = it.data
                    shared.token = it.token
                    shared.hasToken = true
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    activity?.finish()
                    Log.d("SSNSNWJNJNDNWENBU", "observe: $it")
                }
                if (k.error.isNotEmpty()) {
                    hideStartProgress()
                    Log.d("SSNSNWJNJNDNWENBU", "observe: ${k.error}")
                    Toast.makeText(requireContext(), k.error, Toast.LENGTH_SHORT).show()
                    if (k.error == "Student already exists") {
                        navigate(R.id.loginFragment)
                    }
                }
            }
        }
    }

    private fun updatePassword() {
        resetViewModel.forgotPassword(ForgotPasswordModel(phone, password))
        lifecycleScope.launchWhenStarted {
            resetViewModel.password.collectLatest { k ->
                k.data?.let {
                    hideStartProgress()
                    shared.user = it.data
                    shared.token = it.token
                    shared.hasToken = true
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    activity?.finish()
                    Log.d("SSNSNWJNJNDNWENBU", "observe: $it")
                }
                if (k.error.isNotEmpty()) {
                    hideStartProgress()
                    Log.d("SSNSNWJNJNDNWENBU", "observe: ${k.error}")
                    Toast.makeText(requireContext(), k.error, Toast.LENGTH_SHORT).show()
                    if (k.error == "Student already exists") {
                        navigate(R.id.loginFragment)
                    }
                }
            }
        }
    }

    private fun validation(): Boolean {
        return if (password.matches(".*[A-Za-z\\d].*".toRegex()) && password.length >= 8 && password == confirmPassword) {
            binding.next.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.main_blue
                )
            )
            binding.rule.gone()
            binding.next.cardView.isClickable = true
            binding.next.cardView.setOnClickListener {
                if (type == 0) {
                    registerUser()
                } else if (type == 1) {
                    updatePassword()
                }
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