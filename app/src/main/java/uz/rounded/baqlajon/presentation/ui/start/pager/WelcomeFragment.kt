package uz.rounded.baqlajon.presentation.ui.start.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.databinding.FragmentWelcomeBinding
import uz.rounded.baqlajon.presentation.ui.start.pager.adapter.PagerModel
import uz.rounded.baqlajon.presentation.ui.start.pager.adapter.WelcomeAdapter
import uz.roundedllc.tmkeld.presentation.BaseFragment

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>() {
    private lateinit var adapter: WelcomeAdapter
    private var pos = 0
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWelcomeBinding = FragmentWelcomeBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        setViewPager()
        binding.skip.setOnClickListener {
            navigate(R.id.action_welcomeFragment_to_authFragment)
        }
        binding.next.setOnClickListener {
            binding.viewPager.setCurrentItem(++pos, true)
        }
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                pos = position
            }

            override fun onPageSelected(position: Int) = Unit

            override fun onPageScrollStateChanged(state: Int) = Unit

        })
    }

    private fun setViewPager() {
        adapter = WelcomeAdapter(
            requireContext(), listOf(
                PagerModel(
                    R.string.find_your_favorite_course,
                    R.string.sub_label,
                    R.drawable.pager_1
                ),
                PagerModel(
                    R.string.learn_with_fun,
                    R.string.sub_label,
                    R.drawable.pager_2
                ),
                PagerModel(
                    R.string.get_good_results,
                    R.string.sub_label,
                    R.drawable.pager_3
                )
            )
        )
        binding.viewPager.adapter = adapter
    }
}