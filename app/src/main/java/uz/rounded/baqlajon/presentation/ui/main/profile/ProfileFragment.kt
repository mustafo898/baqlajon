package uz.rounded.baqlajon.presentation.ui.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.databinding.FragmentProfileBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }

}