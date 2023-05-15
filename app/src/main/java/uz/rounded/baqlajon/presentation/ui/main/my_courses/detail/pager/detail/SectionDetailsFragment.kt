package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.gone
import uz.rounded.baqlajon.core.extensions.loadImage
import uz.rounded.baqlajon.core.extensions.navigateWithArgs
import uz.rounded.baqlajon.core.extensions.visible
import uz.rounded.baqlajon.databinding.FragmentSectionDetailsBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment

@AndroidEntryPoint
class SectionDetailsFragment : BaseFragment<FragmentSectionDetailsBinding>() {

    private val viewModel: DetailsViewModel by viewModels()
    private var lessonUrl = ""

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentSectionDetailsBinding.inflate(inflater)

    private var id = ""
    private var isOpen = false

    override fun created(view: View, savedInstanceState: Bundle?) {

        if (arguments != null) {
            id = requireArguments().getString("ID") as String
        }

        observe()
        playVideo()
        binding.btn.cardView.setOnClickListener {
            viewModel.finishVideo(id)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.finish.collectLatest {
                it.data?.let {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun playVideo() = binding.play.setOnClickListener {
        navigateWithArgs(
            R.id.action_sectionDetailsFragment_to_playerFragment, bundleOf("ID" to lessonUrl)
        )
    }

    private fun observe() {
        viewModel.getDetailVideo(id)

        lifecycleScope.launchWhenStarted {
            viewModel.detail.collectLatest {
                it.data?.let { p ->
                    lessonUrl = buildString {
                        append(p.videoUrl)
                    }

                    binding.apply {
                        title.text = p.title
                        desc.text = p.description
                        if (p.isFree) {
                            isOpen = true
//                            setVideo(getString(R.string.base_url) + "public/uploads" + p.videoUrl)
                            notification.gone()
                        } else {
                            isOpen = false
                            notification.visible()
                        }
                        image2.loadImage(requireContext(), p.imageUrl)
                    }
                    hideMainProgress()
                }
                if (it.isLoading) {
                    showMainProgress()
                }
                if (it.error.isNotBlank()) {
                    hideMainProgress()
                }
            }
        }
    }

}