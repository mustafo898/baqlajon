package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.exo_player_fullscreen_controller_view.view.*
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.blockClickable
import uz.rounded.baqlajon.core.extensions.popBackStack
import uz.rounded.baqlajon.databinding.FragmentPlayerBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.fullscreen.FullScreenPlayerActivity

class PlayerFragment : BaseFragment<FragmentPlayerBinding>(), View.OnClickListener,
    FullScreenPlayerActivity.OnFinishListener {

    private lateinit var player: SimpleExoPlayer
    var url = ""
    var url_test =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentPlayerBinding = FragmentPlayerBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            url = it.getString("ID").toString()
        }
        Log.d("KJJNDKS", "created: $url")
        hideMainProgress()
        player = ExoPlayerFactory.newSimpleInstance(context)
        binding.onClick = this
        binding.description = context?.getString(R.string.video_player)
        binding.playerView.player = player
        binding.playerView.exo_fullscreen_icon.setOnClickListener(this)

        val dataSource = DefaultDataSourceFactory(context, Util.getUserAgent(context, "Baqlajon"))
        val mediaSource = ExtractorMediaSource.Factory(dataSource)
            .setExtractorsFactory(DefaultExtractorsFactory())
            .createMediaSource(
                Uri.parse(/*resources.getString(R.string.base_url) + "public/uploads" +*/url)
            )
        Log.d("FKJDFKS", "created player: $url")
        player.prepare(mediaSource, true, false)
    }

    override fun onClick(p0: View?) {
        p0?.blockClickable()
        when (p0?.id) {
            R.id.back -> popBackStack()
            R.id.exo_fullscreen_icon -> {
                activity?.let {
                    FullScreenPlayerActivity.player = player
                    FullScreenPlayerActivity.listener = this
                    val intent = Intent(it, FullScreenPlayerActivity::class.java)
                    startActivity(intent/*, option.toBundle()*/)
                    binding.playerView.player = null
                }
            }
        }
    }

    override fun onFullScreenFinish() {
        binding.playerView.player = player
    }

    override fun onResume() {
        super.onResume()
        player.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()
        player.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}