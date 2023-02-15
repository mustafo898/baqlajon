package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getColor
import uz.rounded.baqlajon.databinding.FragmentCourseDetailsBinding
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.adapter.CoursePagerAdapter
import uz.roundedllc.tmkeld.presentation.BaseFragment

class CourseDetailsFragment : BaseFragment<FragmentCourseDetailsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentCourseDetailsBinding.inflate(inflater)

    private lateinit var adapter: CoursePagerAdapter

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.viewPager.apply {
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        
        adapter = CoursePagerAdapter(childFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    setVideos()
                } else {
                    setReviews()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        setVideos()
        changeViewPager()
    }

    private fun changeViewPager() {
        binding.reviewsView.setOnClickListener {
            setReviews()
            binding.viewPager.setCurrentItem(1, true)
        }

        binding.videosView.setOnClickListener {
            setVideos()
            binding.viewPager.setCurrentItem(0, true)
        }
    }

    private fun setVideos() = binding.apply {
        videosView.setCardBackgroundColor(getColor(requireContext(), R.color.blue))
        videosView.strokeColor = getColor(requireContext(), R.color.blue)
        videoText.setTextColor(getColor(requireContext(), R.color.white))
        reviewText.setTextColor(getColor(requireContext(), R.color.grey2))
        reviewsView.setCardBackgroundColor(getColor(requireContext(), R.color.white))
        reviewsView.strokeColor = getColor(requireContext(), R.color.grey2)
        btn.name = getString(R.string.start_course)
    }

    private fun setReviews() = binding.apply {
        videosView.setCardBackgroundColor(getColor(requireContext(), R.color.white))
        videosView.strokeColor = getColor(requireContext(), R.color.grey2)
        videoText.setTextColor(getColor(requireContext(), R.color.grey2))
        reviewText.setTextColor(getColor(requireContext(), R.color.white))
        reviewsView.setCardBackgroundColor(getColor(requireContext(), R.color.blue))
        reviewsView.strokeColor = getColor(requireContext(), R.color.blue)
        btn.name = getString(R.string.write_a_review)
    }
}