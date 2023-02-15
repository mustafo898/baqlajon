package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentReviewsBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment

class ReviewsFragment : BaseFragment<FragmentReviewsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentReviewsBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }
}