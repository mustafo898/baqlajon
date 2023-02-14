package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentSectionDetailsBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment

class SectionDetailsFragment : BaseFragment<FragmentSectionDetailsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSectionDetailsBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }

}