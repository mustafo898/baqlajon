package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.gone
import uz.rounded.baqlajon.core.extensions.loadImage
import uz.rounded.baqlajon.core.extensions.visible
import uz.rounded.baqlajon.databinding.FragmentSectionDetailsBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment

@AndroidEntryPoint
class SectionDetailsFragment : BaseFragment<FragmentSectionDetailsBinding>() {

    private val viewModel: DetailsViewModel by viewModels()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSectionDetailsBinding.inflate(inflater)

    private val controller by lazy {
        MediaController(requireContext())
    }

    private var id = ""

    override fun created(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            id = it.getString("ID", "")
        }

        observe()

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

    private fun observe() {
        viewModel.getDetailVideo(id)

        lifecycleScope.launchWhenStarted {
            viewModel.detail.collectLatest {
                it.data?.let { p ->
                    binding.apply {
                        title.text = p.title
                        desc.text = p.description
                        if (p.isFree) {
                            notification.gone()
                        } else {
                            setVideo(p.videoUrl)
                            notification.visible()
                        }
                        image2.loadImage(requireContext(), p.imageUrl)
                    }
                    hideProgress()
                }
                if (it.isLoading) {
                    showProgress()
                }
                if (it.error.isNotBlank()) {
                    hideProgress()
                }
            }
        }
    }

    private fun setVideo(path: String) {
        binding.video.setVideoPath(getString(R.string.base_url) + path)
        binding.video.setMediaController(controller)
        controller.setAnchorView(binding.video)
    }
}