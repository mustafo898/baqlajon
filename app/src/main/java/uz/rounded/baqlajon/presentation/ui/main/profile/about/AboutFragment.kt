package uz.rounded.baqlajon.presentation.ui.main.profile.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.databinding.FragmentAboutBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment

class AboutFragment : BaseFragment<FragmentAboutBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAboutBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }
}