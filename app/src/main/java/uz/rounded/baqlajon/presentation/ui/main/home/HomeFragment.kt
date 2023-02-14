package uz.rounded.baqlajon.presentation.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.databinding.FragmentHomeBinding
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.HomeCategoryAdapter
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.HomeCourseAdapter
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.HomeCourseSecondAdapter
import uz.roundedllc.tmkeld.presentation.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val adapter by lazy {
        HomeCourseAdapter {

        }
    }

    private val adapterCategory by lazy {
        HomeCategoryAdapter {

        }
    }

    private val adapterList by lazy {
        HomeCourseSecondAdapter {

        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        setAdapter()

        val list = mutableListOf<String>()
        for (i in 0 until 10) {
            list.add("")
        }

        adapter.submitList(list)
        adapterList.submitList(list)
        adapterCategory.submitList(list)

        binding.search.setOnClickListener {
            navigate(R.id.action_homeFragment_to_searchFragment)
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