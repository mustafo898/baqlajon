package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.databinding.FragmentCourseSectionsBinding
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.adapter.CourseSectionsAdapter
import uz.rounded.baqlajon.presentation.ui.BaseFragment

class CourseSectionsFragment : BaseFragment<FragmentCourseSectionsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCourseSectionsBinding.inflate(inflater)

    private val adapter by lazy {
        CourseSectionsAdapter()
    }

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.list.adapter = adapter
        val list = mutableListOf<String>()
        for (i in 0 until 10) {
            list.add("")
        }
        adapter.setList(list)

        adapter.setItemClickListener {
            navigate(R.id.action_courseDetailsFragment_to_sectionDetailsFragment)
        }
    }
}