package uz.rounded.baqlajon.presentation.ui.main.my_courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentMyCoursesBinding
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.HomeCategoryAdapter
import uz.rounded.baqlajon.presentation.ui.main.my_courses.adapter.MyCourseAdapter
import uz.roundedllc.tmkeld.presentation.BaseFragment

class MyCoursesFragment : BaseFragment<FragmentMyCoursesBinding>() {


    private val adapter by lazy {
        MyCourseAdapter {

        }
    }

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMyCoursesBinding = FragmentMyCoursesBinding.inflate(inflater)


    override fun created(view: View, savedInstanceState: Bundle?) {


    }

}