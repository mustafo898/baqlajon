package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(
    val name: String,
    val path: String,
    val duration: Long,
    val id: Long,
    val size: Double,
    val type: String,
    val date_added: String,
    val resolution: String?,
    val height: Int,
    val width: Int
) : Parcelable