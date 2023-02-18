package uz.rounded.baqlajon.presentation.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.core.extensions.navigateWithArgs
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.databinding.FragmentHomeBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.CategoryModel
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.HomeCategoryAdapter
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.HomeCourseAdapter
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.HomeCourseSecondAdapter
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var sharedPreference: SharedPreference

    private val adapter by lazy {
        HomeCourseAdapter {

        }
    }

    private val adapterCategory by lazy {
        HomeCategoryAdapter(requireContext()) {
            getCategoryList(it)
        }
    }

    private val adapterList by lazy {
        HomeCourseSecondAdapter(requireContext()) {
            navigateWithArgs(
                R.id.action_homeFragment_to_courseDetailsFragment, bundleOf("ID" to it)
            )
        }
    }

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
//        getProfile()
        setAdapter()
        setCategory()
        getAllCourse()

        binding.search.setOnClickListener {
            navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun setCategory() {
        val category = mutableListOf<CategoryModel>()

        category.add(CategoryModel(true, "All courses"))
        category.add(CategoryModel(false, "Popular"))
        category.add(CategoryModel(false, "Newest"))

        adapterCategory.submitList(category)
    }

    private fun getCategoryList(pos: Int) {
        when (pos) {
            0 -> getAllCourse()
            1 -> getPopular()
            2 -> getNewest()
        }
    }

//    private fun getProfile() {
//        viewModel.getProfile()
//
//        lifecycleScope.launchWhenStarted {
//            viewModel.user.collectLatest {
//                it.data?.let { p ->
//                    sharedPreference.user = p
//                }
//                if (it.error.isNotBlank()) {
//                    Log.d("sdksfkhsjlddhj", "observe: ${it.error}")
//                    hideProgress()
//                }
//            }
//        }
//    }

    private fun getAllCourse() {
        viewModel.getAllCourse()

        lifecycleScope.launchWhenStarted {
            viewModel.allCourse.collectLatest {
                it.data?.let { p ->
                    adapterList.submitList(p)
                }
                if (it.error.isNotBlank()) {
                    Log.d("sdksfkhsjlddhj", "observe: ${it.error}")
                    hideProgress()
                }
            }
        }
    }

    private fun getPopular() {
        viewModel.getPopular()

        lifecycleScope.launchWhenStarted {
            viewModel.popular.collectLatest {
                it.data?.let { p ->
                    adapterList.submitList(p)
                }
            }
        }
    }

    private fun getNewest() {
        viewModel.getNewest()

        lifecycleScope.launchWhenStarted {
            viewModel.newest.collectLatest {
                it.data?.let { p ->
                    adapterList.submitList(p)
                }
            }
        }
    }

    private fun setAdapter() = binding.apply {
        val snapper = LinearSnapHelper()
        snapper.attachToRecyclerView(binding.courseList)

        courseList.adapter = adapter
        list.adapter = adapterList
        categoryList.adapter = adapterCategory
    }

}