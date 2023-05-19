package uz.rounded.baqlajon.presentation.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import com.github.chrisbanes.photoview.PhotoView

class PhotoView(ctx: Context, private val drwb: Drawable) : AlertDialog(ctx) {
    init {
        /* val width = Resources.getSystem().displayMetrics.widthPixels
         val height = Resources.getSystem().displayMetrics.heightPixels*/
        val iv = PhotoView(ctx).apply {
            layoutParams = FrameLayout.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            isClickable = true
            isFocusable = true
            setPadding(16, 16, 16, 16)
            setImageDrawable(drwb)
        }
        // window?.setLayout(width, height)
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setView(iv)
    }
}