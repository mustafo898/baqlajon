package uz.rounded.baqlajon.presentation.ui.start.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.databinding.FragmentLoginBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

        binding.login.cardView.setOnClickListener {
            val phoneNumber = binding.input.editText.text.toString()
            val password = binding.password.text.toString()
        }
        binding.signUp.setOnClickListener {
            navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

}