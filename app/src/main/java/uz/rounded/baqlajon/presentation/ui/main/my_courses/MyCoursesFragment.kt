package uz.rounded.baqlajon.presentation.ui.main.my_courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.baqlajon.databinding.FragmentMyCoursesBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.CategoryModel
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.HomeCategoryAdapter
import uz.rounded.baqlajon.presentation.ui.main.my_courses.adapter.MyCourseAdapter

@AndroidEntryPoint
class MyCoursesFragment : BaseFragment<FragmentMyCoursesBinding>() {

    private val viewModel: MyCoursesViewModel by viewModels()

    private val adapter by lazy {
        MyCourseAdapter(requireContext()) {

        }
    }

    private val adapterCategory by lazy {
        HomeCategoryAdapter(requireContext()) {
            getByCategory(it)
        }
    }

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMyCoursesBinding = FragmentMyCoursesBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.list.adapter = adapter
        binding.categoryList.adapter = adapterCategory

        setCategory()
        getAllCourse()
    }

    private fun setCategory() {
        val category = mutableListOf<CategoryModel>()

        category.add(CategoryModel(true, "All courses"))
        category.add(CategoryModel(false, "Ongoing"))
        category.add(CategoryModel(false, "Completed"))

        adapterCategory.submitList(category)
    }

    private fun getAllCourse() {
        viewModel.getAllMyCourse()

        lifecycleScope.launchWhenStarted {
            viewModel.allCourse.collectLatest {
                it.data?.let { p ->
                    adapter.submitList(p)
                }
            }
        }
    }

    private fun getByCategory(pos: Int) {
        when (pos) {
            0 -> getAllCourse()
            1 -> getStatus("start")
            2 -> getStatus("finish")
        }
    }

    private fun getStatus(string: String) {
        viewModel.getStatus(string)

        lifecycleScope.launchWhenStarted {
            viewModel.status.collectLatest {
                it.data?.let { p ->
                    adapter.submitList(p)
                }
            }
        }
    }
}