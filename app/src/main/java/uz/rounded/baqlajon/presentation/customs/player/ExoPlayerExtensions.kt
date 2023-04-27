package uz.rounded.baqlajon.presentation.customs.player

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.decorViewSYSTEM_UI_FLAG_FULLSCREEN
import uz.rounded.baqlajon.core.extensions.decorViewSYSTEM_UI_FLAG_VISIBLE

@SuppressLint("SourceLockedOrientationActivity")
fun SimpleExoPlayer.preparePlayer(
    playerView: PlayerView,
    forceLandscape: Boolean = false,
    activity: Activity
) {
    (playerView.context).apply {
        val playerViewFullscreen = PlayerView(this)
        var isPlaying = false
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        playerViewFullscreen.layoutParams = layoutParams
        playerViewFullscreen.visibility = View.GONE
        playerViewFullscreen.setBackgroundColor(resources.getColor(R.color.black))
        (playerView.rootView as ViewGroup).apply { addView(playerViewFullscreen, childCount) }
        val fullScreenButton: ImageView = playerView.findViewById(R.id.exo_fullscreen_icon)
        val play: ImageView = playerView.findViewById(R.id.exo_play)
        play.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.play_fill2
            )
        )
        val normalScreenButton: ImageView =
            playerViewFullscreen.findViewById(R.id.exo_fullscreen_icon)
        fullScreenButton.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_fullscreen_open
            )
        )
        normalScreenButton.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_fullscreen_close
            )
        )
        fullScreenButton.setOnClickListener {
            activity.decorViewSYSTEM_UI_FLAG_FULLSCREEN()

            if (forceLandscape)
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            playerView.visibility = View.GONE
            playerViewFullscreen.visibility = View.VISIBLE
            PlayerView.switchTargetView(this@preparePlayer, playerView, playerViewFullscreen)
        }
        normalScreenButton.setOnClickListener {
            activity.decorViewSYSTEM_UI_FLAG_VISIBLE()
            activity.actionBar?.hide()
            if (forceLandscape)
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            normalScreenButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_fullscreen_close
                )
            )
            playerView.visibility = View.VISIBLE
            playerViewFullscreen.visibility = View.GONE
            PlayerView.switchTargetView(this@preparePlayer, playerViewFullscreen, playerView)
        }
        play.setOnClickListener {
            if (!isPlaying) {
                MediaPlayer.startPlayer()
                isPlaying = true
            } else {
                MediaPlayer.pausePlayer()
                isPlaying = false
            }
        }
        playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT
        playerView.player = this@preparePlayer
    }
}

fun SimpleExoPlayer.setSource(context: Context, url: String) {
    val dataSourceFactory: DataSource.Factory =
        DefaultDataSourceFactory(context, Util.getUserAgent(context, "app"))
    val videoSource: MediaSource =
        if (url.endsWith("m3u8") || url.endsWith("m3u"))
            HlsMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(url))
        else
            ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(url))
    this.prepare(videoSource)
}