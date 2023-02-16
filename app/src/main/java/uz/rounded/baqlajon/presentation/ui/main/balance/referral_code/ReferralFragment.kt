package uz.rounded.baqlajon.presentation.ui.main.balance.referral_code

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.databinding.FragmentReferralBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment


class ReferralFragment : BaseFragment<FragmentReferralBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReferralBinding = FragmentReferralBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }

}