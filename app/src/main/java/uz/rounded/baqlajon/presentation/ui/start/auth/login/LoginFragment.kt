package uz.rounded.baqlajon.presentation.ui.start.auth.login

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
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.databinding.FragmentLoginBinding
import uz.rounded.baqlajon.domain.model.auth.login.LoginModel
import uz.rounded.baqlajon.presentation.MainActivity
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater)

    private var phoneLength = false
    private var phoneString = ""
    private var password = ""

    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var shared: SharedPreference
    override fun created(view: View, savedInstanceState: Bundle?) {

        binding.login.cardView.setCardBackgroundColor(
            getColor(
                requireContext(), R.color.button_disabled
            )
        )
        binding.login.cardView.isClickable = false

        binding.forgotPassword.setOnClickListener {
            navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        observe()
        validateFields()
    }

    private fun observe() {
        binding.login.cardView.setOnClickListener {
            phoneString.replace(" ", "")
            if (validation()) {
                viewModel.login(LoginModel(phoneNumber = phoneString, password = password))
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.login.collectLatest { k ->
                k.data?.let {
                    hideStartProgress()

                    shared.user = it.data
                    shared.hasToken = true
                    shared.token = it.token

                    Log.d("IIIIII", "registration: $it")
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    activity?.finish()
                }
                if (k.isLoading) {
                    showStartProgress()
                }
                if (k.error.isNotEmpty()) {
                    hideStartProgress()
                    Log.d("IIIIII", "registration: ${k.error}")
                    Toast.makeText(requireContext(), k.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validation(): Boolean {
        return if (password.isNotEmpty() && phoneLength) {
            binding.login.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.main_blue
                )
            )
            binding.login.cardView.isClickable = true
            true
        } else {
            binding.login.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.button_disabled
                )
            )
            binding.login.cardView.isClickable = false
            false
        }
    }

    private fun validateFields() {
        binding.phoneNumber.addTextChangedListener {
            phoneLength = it.toString().length == 17
            validation()
            phoneString = it.toString().trim()

        }

        binding.password.addTextChangedListener {
            password = it.toString().trim()
            validation()
        }
    }

}