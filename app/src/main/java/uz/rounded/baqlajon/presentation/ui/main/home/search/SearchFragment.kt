package uz.rounded.baqlajon.presentation.ui.main.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentSearchBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }

}