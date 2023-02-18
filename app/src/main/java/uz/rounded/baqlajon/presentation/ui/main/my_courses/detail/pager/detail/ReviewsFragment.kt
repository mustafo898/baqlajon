package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.popBackStack
import uz.rounded.baqlajon.databinding.FragmentReviewsBinding
import uz.rounded.baqlajon.domain.model.main.course.RequestCommentModel
import uz.rounded.baqlajon.presentation.ui.BaseFragment

@AndroidEntryPoint
class ReviewsFragment : BaseFragment<FragmentReviewsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentReviewsBinding.inflate(inflater)

    private val viewModel: DetailsViewModel by viewModels()

    private var id = ""

    override fun created(view: View, savedInstanceState: Bundle?) {
        hideProgress()

        arguments?.let {
            id = it.getString("ID", "")
        }

        binding.signUp.cardView.setOnClickListener {
            if (binding.txt.text.isNotEmpty() && binding.rating.progress > 0) {
                viewModel.createComment(
                    id, RequestCommentModel(binding.txt.text.toString(), binding.rating.progress)
                )
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.create.collectLatest {
                it.data?.let {
                    popBackStack()
                }
            }
        }

        binding.apply {
            rating.stepSize = 1.0F

            rating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                when (rating) {
                    0.0F -> {
                        binding.emoji.setImageResource(R.drawable.very_bad)
                        binding.desc.text = getString(R.string.very_bad)
                    }
                    1.0F -> {
                        binding.emoji.setImageResource(R.drawable.very_bad)
                        binding.desc.text = getString(R.string.very_bad)
                    }
                    2.0F -> {
                        binding.emoji.setImageResource(R.drawable.bad)
                        binding.desc.text = getString(R.string.bad)
                    }
                    3.0F -> {
                        binding.emoji.setImageResource(R.drawable.ok)
                        binding.desc.text = getString(R.string.ok)
                    }
                    4.0F -> {
                        binding.emoji.setImageResource(R.drawable.good)
                        binding.desc.text = getString(R.string.good)
                    }
                    5.0F -> {
                        binding.emoji.setImageResource(R.drawable.very_good)
                        binding.desc.text = getString(R.string.great)
                    }
                }
            }
        }
    }
}