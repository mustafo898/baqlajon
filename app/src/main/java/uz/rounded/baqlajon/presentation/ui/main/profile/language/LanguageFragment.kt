package uz.rounded.baqlajon.presentation.ui.main.profile.language

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.databinding.FragmentLanguageBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment

class LanguageFragment : BaseFragment<FragmentLanguageBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLanguageBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }

}