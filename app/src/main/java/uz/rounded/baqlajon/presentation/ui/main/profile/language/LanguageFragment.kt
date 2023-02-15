package uz.rounded.baqlajon.presentation.ui.main.profile.language

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentLanguageBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment

class LanguageFragment : BaseFragment<FragmentLanguageBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLanguageBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }

}