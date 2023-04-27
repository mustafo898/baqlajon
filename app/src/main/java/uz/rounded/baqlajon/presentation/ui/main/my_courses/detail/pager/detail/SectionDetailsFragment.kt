package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.gone
import uz.rounded.baqlajon.core.extensions.loadImage
import uz.rounded.baqlajon.core.extensions.visible
import uz.rounded.baqlajon.databinding.FragmentSectionDetailsBinding
import uz.rounded.baqlajon.presentation.customs.player.MediaPlayer
import uz.rounded.baqlajon.presentation.customs.player.preparePlayer
import uz.rounded.baqlajon.presentation.customs.player.setSource
import uz.rounded.baqlajon.presentation.ui.BaseFragment

@AndroidEntryPoint
class SectionDetailsFragment : BaseFragment<FragmentSectionDetailsBinding>() {

    private val viewModel: DetailsViewModel by viewModels()

//    private lateinit var simpleExoplayer: SimpleExoPlayer

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSectionDetailsBinding.inflate(inflater)

    private var id = ""
    private var isOpen = false

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
                            isOpen = true
                            setVideo(getString(R.string.base_url) + "public/uploads" + p.videoUrl)
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

    private fun setVideo(path: String) {
        Log.d("video url", "setVideo: $path")
//        simpleExoplayer = ExoPlayerFactory.newSimpleInstance(requireActivity())
//        binding.exoPlayer.player = simpleExoplayer
//        val dataSource = DefaultDataSourceFactory(
//            requireContext(), Util.getUserAgent(requireContext(), getString(R.string.app_name))
//        )
//        val mediaSource = ExtractorMediaSource.Factory(dataSource)
//            .createMediaSource(Uri.parse(path))
//
//        simpleExoplayer.prepare(mediaSource)

        MediaPlayer.initialize(requireContext())
        MediaPlayer.exoPlayer?.preparePlayer(binding.exoPlayer, true, requireActivity())
        MediaPlayer.exoPlayer?.setSource(
            requireContext(),
            Uri.parse(path).toString()
        )
    }

    override fun onPause() {
        super.onPause()
        Log.d("DJFKNSF", "onPause: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isOpen) {
            MediaPlayer.stopPlayer()
        }
    }
}