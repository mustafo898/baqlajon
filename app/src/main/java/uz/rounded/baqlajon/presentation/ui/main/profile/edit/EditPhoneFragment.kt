package uz.rounded.baqlajon.presentation.ui.main.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentEditPhoneBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment

class EditPhoneFragment : BaseFragment<FragmentEditPhoneBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentEditPhoneBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }
}