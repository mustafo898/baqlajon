package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.callback

import com.google.android.exoplayer2.Player

interface PlayerStateCallback {

    fun onVideoDurationRetrieved(duration: Long, player: Player)

    fun onVideoBuffering(player: Player)

    fun onStartedPlaying(player: Player)

    fun onFinishedPlaying(player: Player)
}