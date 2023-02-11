package uz.rounded.baqlajon.presentation.ui.start.auth.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.core.extensions.popBackStack
import uz.rounded.baqlajon.databinding.FragmentRegistrationBinding
import uz.rounded.baqlajon.presentation.MainActivity
import uz.rounded.baqlajon.presentation.StartActivity
import uz.roundedllc.tmkeld.presentation.BaseFragment

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegistrationBinding = FragmentRegistrationBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

        binding.signUp.cardView.setOnClickListener {
            navigate(R.id.action_registrationFragment_to_smsVerifyFragment)
        }

        binding.login.setOnClickListener {
            popBackStack()
        }
    }
}