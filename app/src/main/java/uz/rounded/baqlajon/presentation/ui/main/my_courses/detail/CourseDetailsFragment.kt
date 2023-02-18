package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getColor
import uz.rounded.baqlajon.core.extensions.loadImage
import uz.rounded.baqlajon.core.extensions.navigateWithArgs
import uz.rounded.baqlajon.databinding.FragmentCourseDetailsBinding
import uz.rounded.baqlajon.domain.model.main.course.VideoModel
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.adapter.CoursePagerAdapter

@AndroidEntryPoint
class CourseDetailsFragment : BaseFragment<FragmentCourseDetailsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentCourseDetailsBinding.inflate(inflater)

    private val viewModel: CourseDetailsViewModel by viewModels()

    private lateinit var adapter: CoursePagerAdapter

    private var id = ""
    private var isOpen = true

    override fun created(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            id = it.getString("ID", "")
        }

        observe()

        binding.viewPager.apply {
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        binding.btn.cardView.setOnClickListener {
            if (isOpen) {
                viewModel.startLesson(id)
            } else {
                navigateWithArgs(
                    R.id.action_courseDetailsFragment_to_reviewsFragment,
                    bundleOf("ID" to id)
                )
            }
        }

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

    @SuppressLint("SetTextI18n")
    private fun observe() {
        viewModel.getDetail(id)

        lifecycleScope.launchWhenStarted {
            viewModel.detail.collectLatest {
                it.data?.let { p ->

                    binding.apply {
                        title.text = p.title
                        desc.text = p.description
                        hours.text = timeFormat(p.time)
                        teacherName.text = p.author.firstName + " " + p.author.lastName
                        teacherType.text = p.author.description
                        teacherImage.loadImage(requireContext(), p.author.image)
                        image.loadImage(requireContext(), p.image)
                        videos.text = getString(R.string.videos, p.videoCount.toString())
                        students.text =
                            getString(R.string.students_learnt, p.studentCount.toString())
                        comment.text = getString(R.string.reviews, p.comment.size.toString())
                    }

                    val list = mutableListOf<VideoModel>()

                    list.addAll(p.freeVideo)
                    list.addAll(p.video)

                    adapter = CoursePagerAdapter(p.comment, list, childFragmentManager, lifecycle)

                    binding.viewPager.adapter = adapter

                    hideProgress()
                }
                if (it.isLoading) {
                    showProgress()
                }
                if (it.error.isNotBlank()) {
                    Log.d("sdksfkhsjlddhj", "observe: ${it.error}")
                    hideProgress()
                }
            }
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
        isOpen = true
    }

    private fun setReviews() = binding.apply {
        videosView.setCardBackgroundColor(getColor(requireContext(), R.color.white))
        videosView.strokeColor = getColor(requireContext(), R.color.grey2)
        videoText.setTextColor(getColor(requireContext(), R.color.grey2))
        reviewText.setTextColor(getColor(requireContext(), R.color.white))
        reviewsView.setCardBackgroundColor(getColor(requireContext(), R.color.blue))
        reviewsView.strokeColor = getColor(requireContext(), R.color.blue)
        btn.name = getString(R.string.write_a_review)
        isOpen = false
    }

    private fun timeFormat(time: Int): String {
        val hour = time / 3600
        val minute = time / 60
        val hourFormat = if (hour < 1) "" else "${hour}h"
        val minuteFormat = if (minute < 10) "0${minute}" else "$minute"
        return "$hourFormat${minuteFormat}min"
    }
}