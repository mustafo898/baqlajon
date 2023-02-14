package uz.rounded.baqlajon.presentation.ui.main.profile.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.databinding.FragmentEditPasswordBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment

class EditPasswordFragment : BaseFragment<FragmentEditPasswordBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentEditPasswordBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }
}