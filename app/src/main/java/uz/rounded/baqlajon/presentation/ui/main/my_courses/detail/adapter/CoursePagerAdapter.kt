package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.reviews.CourseReviewsFragment
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.sections.CourseSectionsFragment

class CoursePagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                CourseSectionsFragment()
            }
            1 -> {
                CourseReviewsFragment()
            }
            else -> {
                CourseSectionsFragment()
            }
        }
    }

}