package uz.rounded.baqlajon.presentation.ui.start.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.databinding.FragmentAuthBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment


class AuthFragment : BaseFragment<FragmentAuthBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAuthBinding = FragmentAuthBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            login.cardView.setOnClickListener {
                navigate(R.id.action_authFragment_to_loginFragment)
            }
            signUp.cardView.setOnClickListener {
                navigate(R.id.action_authFragment_to_registrationFragment)
            }
        }
    }

}