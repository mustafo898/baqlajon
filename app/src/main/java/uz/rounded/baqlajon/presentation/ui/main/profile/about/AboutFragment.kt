package uz.rounded.baqlajon.presentation.ui.main.profile.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentAboutBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment

class AboutFragment : BaseFragment<FragmentAboutBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAboutBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        hideMainProgress()
    }
}