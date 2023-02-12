package uz.rounded.baqlajon.presentation.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import uz.rounded.baqlajon.databinding.FragmentHomeBinding
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
        HomeCategoryAdapter()
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
        for (i in 0 until 10) {
            list.add("")
        }

        adapter.setList(list)
        adapterList.setList(list)
        adapterCategory.setList(list)
    }


}