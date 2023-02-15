package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.databinding.FragmentReviewsBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment

class ReviewsFragment : BaseFragment<FragmentReviewsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentReviewsBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }
}