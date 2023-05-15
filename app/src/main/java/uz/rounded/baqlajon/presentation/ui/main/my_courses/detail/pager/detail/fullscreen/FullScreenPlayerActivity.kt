package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.fullscreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.exo_player_fullscreen_controller_view.view.*
import uz.rounded.baqlajon.databinding.ActivityFullscreenPlayerBinding

@AndroidEntryPoint
class FullScreenPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullscreenPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.setOnSystemUiVisibilityChangeListener {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
        player?.let { binding.playerView.player = it }
        binding.playerView.exo_fullscreen_icon?.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        player?.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()
        player?.playWhenReady = false
    }

    companion object {
        var player: SimpleExoPlayer? = null
        var listener: OnFinishListener? = null
    }

    interface OnFinishListener {
        fun onFullScreenFinish()
    }

    override fun onDestroy() {
        super.onDestroy()
        listener?.onFullScreenFinish()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }
}