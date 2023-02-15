package uz.rounded.baqlajon.presentation.ui.start.auth.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentForgotPasswordBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment

class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentForgotPasswordBinding = FragmentForgotPasswordBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }
}