package uz.rounded.baqlajon.presentation.ui.main.my_courses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.databinding.FragmentMyCoursesBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment

class MyCoursesFragment : BaseFragment<FragmentMyCoursesBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyCoursesBinding = FragmentMyCoursesBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }

}