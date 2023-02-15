package uz.rounded.baqlajon.presentation.ui.main.my_courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentMyCoursesBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.CategoryModel
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.HomeCategoryAdapter
import uz.rounded.baqlajon.presentation.ui.main.my_courses.adapter.MyCourseAdapter

class MyCoursesFragment : BaseFragment<FragmentMyCoursesBinding>() {


    private val adapter by lazy {
        MyCourseAdapter {

        }
    }

    private val adapterCategory by lazy {
        HomeCategoryAdapter(requireContext()) {

        }
    }

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMyCoursesBinding = FragmentMyCoursesBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.list.adapter = adapter
        binding.categoryList.adapter = adapterCategory

        val category = mutableListOf<CategoryModel>()

        category.add(CategoryModel(true, "All courses"))
        category.add(CategoryModel(false, "Ongoing"))
        category.add(CategoryModel(false, "Completed"))

        val list = mutableListOf<String>()
        for (i in 0 until 10) {
            list.add("")
        }

        adapter.submitList(list)
        adapterCategory.submitList(category)
    }

}