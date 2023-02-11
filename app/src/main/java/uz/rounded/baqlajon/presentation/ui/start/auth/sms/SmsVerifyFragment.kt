package uz.rounded.baqlajon.presentation.ui.start.auth.sms

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentSmsVerifyBinding
import uz.rounded.baqlajon.presentation.MainActivity
import uz.rounded.baqlajon.presentation.StartActivity
import uz.roundedllc.tmkeld.presentation.BaseFragment

class SmsVerifyFragment : BaseFragment<FragmentSmsVerifyBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSmsVerifyBinding = FragmentSmsVerifyBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.confirm.cardView.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            (activity as StartActivity).finish()
        }
    }
}