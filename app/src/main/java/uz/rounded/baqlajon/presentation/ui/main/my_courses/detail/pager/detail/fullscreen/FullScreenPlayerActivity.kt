package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.fullscreen

import android.view.View
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.android.synthetic.main.activity_fullscreen_player.*
import kotlinx.android.synthetic.main.exo_player_fullscreen_controller_view.view.*
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.presentation.ui.BaseActivity

class FullScreenPlayerActivity : BaseActivity(R.layout.activity_fullscreen_player) {
    override val canChangeTheme: Boolean
        get() = false

    override fun onAfterCreate() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.setOnSystemUiVisibilityChangeListener {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
        player?.let { playerView?.player = it }
        playerView?.exo_fullscreen_icon?.setOnClickListener { finish() }
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