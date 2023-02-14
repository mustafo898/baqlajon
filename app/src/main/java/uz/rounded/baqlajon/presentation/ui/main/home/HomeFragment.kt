package uz.rounded.baqlajon.presentation.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.databinding.FragmentHomeBinding
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.CategoryModel
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.HomeCategoryAdapter
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.HomeCourseAdapter
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.HomeCourseSecondAdapter
import uz.roundedllc.tmkeld.presentation.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater)

    private val adapter by lazy {
        HomeCourseAdapter()
    }

    private val adapterCategory by lazy {
        HomeCategoryAdapter(requireContext())
    }

    private val adapterList by lazy {
        HomeCourseSecondAdapter()
    }

    override fun created(view: View, savedInstanceState: Bundle?) {
        val snapper = LinearSnapHelper()
        snapper.attachToRecyclerView(binding.courseList)

        binding.courseList.adapter = adapter
        binding.list.adapter = adapterList
        binding.categoryList.adapter = adapterCategory

        val list = mutableListOf<String>()
        val category = mutableListOf<CategoryModel>()

        category.add(CategoryModel(true))

        for (i in 0 until 10) {
            list.add("")
            category.add(CategoryModel(false))
        }

        adapter.setList(list)
        adapterList.setList(list)
        adapterCategory.setList(category)

        adapter.setItemClickListener {
            navigate(R.id.action_homeFragment_to_courseDetailsFragment)
        }
    }


}