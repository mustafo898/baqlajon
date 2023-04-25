package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.rounded.baqlajon.domain.model.main.course.CommentModel
import uz.rounded.baqlajon.domain.model.main.course.VideoModel
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.reviews.CourseReviewsFragment
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.sections.CourseSectionsFragment

class CoursePagerAdapter(
    private val comments: List<CommentModel>,
    private val videos: List<VideoModel>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                CourseSectionsFragment(videos)
            }
            1 -> {
                CourseReviewsFragment(comments)
            }
            else -> {
                CourseSectionsFragment(videos)
            }
        }
    }

}