package uz.rounded.baqlajon.presentation.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.databinding.FragmentProfileBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.edit.setOnClickListener {
            navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        binding.payment.setOnClickListener {
            navigate(R.id.action_profileFragment_to_paymentHistoryFragment)
        }

        binding.about.setOnClickListener {
            navigate(R.id.action_profileFragment_to_aboutFragment)
        }

        binding.language.setOnClickListener {
            navigate(R.id.action_profileFragment_to_languageFragment)
        }
    }

}