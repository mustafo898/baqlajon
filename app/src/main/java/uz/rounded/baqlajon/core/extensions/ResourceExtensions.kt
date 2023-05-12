package uz.rounded.baqlajon.core.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import uz.rounded.baqlajon.App.Companion.resources
import java.net.URL

fun getString(@StringRes int: Int) = resources.getString(int)

fun getString(@StringRes int: Int, str: String) = resources.getString(int, str)

fun getColor(context: Context, color: Int): Int {
    return ContextCompat.getColor(
        context,
        color
    )
}

fun getImage(context: Context, string: Int): Drawable? {
    return ContextCompat.getDrawable(
        context,
        string
    )
}

fun URL.getFileSize(): Int? {
    return try {
        openConnection().contentLength
    } catch (x: Exception) {
        null
    }
}