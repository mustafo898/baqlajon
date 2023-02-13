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
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMyCoursesBinding = FragmentMyCoursesBinding.inflate(inflater)

    private val adapterCategory by lazy {
        HomeCategoryAdapter()
    }

    private val adapter by lazy {
        MyCourseAdapter()
    }

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.list.adapter = adapter
        binding.categoryList.adapter = adapterCategory

        val list = mutableListOf<String>()
        for (i in 0 until 10) {
            list.add("")
        }

        adapter.setList(list)
        adapterCategory.setList(list)
    }

}