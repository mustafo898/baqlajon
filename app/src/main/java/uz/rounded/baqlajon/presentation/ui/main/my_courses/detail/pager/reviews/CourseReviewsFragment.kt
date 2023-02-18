package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentCourseReviewsBinding
import uz.rounded.baqlajon.domain.model.main.course.CommentModel
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.adapter.CourseReviewsAdapter

class CourseReviewsFragment(private val list: List<CommentModel>) :
    BaseFragment<FragmentCourseReviewsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCourseReviewsBinding.inflate(inflater)

    private val adapter by lazy {
        CourseReviewsAdapter()
    }

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.list.adapter = adapter
//        val list = mutableListOf<String>()
//        for (i in 0 until 10) {
//            list.add("")
//        }
        adapter.setList(list)
    }
}