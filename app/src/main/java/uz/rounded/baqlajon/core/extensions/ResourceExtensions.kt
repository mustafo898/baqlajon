package uz.rounded.baqlajon.core.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import uz.rounded.baqlajon.App.Companion.resources

fun getString(@StringRes int: Int) = resources.getString(int)

fun getString(@StringRes int: Int, str: String) = resources.getString(int, str)

fun getColor(context: Context, color: Int): Int {
    return ContextCompat.getColor(
        context,
        color
    )
}