package uz.rounded.baqlajon.presentation.ui.main.home.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.databinding.FragmentSearchBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }

}