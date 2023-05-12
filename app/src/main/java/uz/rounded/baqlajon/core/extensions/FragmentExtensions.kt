package uz.rounded.baqlajon.core.extensions

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import uz.rounded.baqlajon.domain.model.DataModel

fun Fragment.popBackStack() {
    findNavController().popBackStack()
}

fun Fragment.navigate(id: Int) {
    findNavController().navigate(id)
}

fun Fragment.navigateWithArgs(id: Int, bundle: Bundle) {
    findNavController().navigate(resId = id, args = bundle)
}

fun objectToJson(data: DataModel): String {
    return Gson().toJson(data)
}

fun jsonToObject(data: String?): DataModel {
    return Gson().fromJson(data ?: "null", DataModel::class.java)
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Activity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager =
            ContextCompat.getSystemService(this, InputMethodManager::class.java)
        inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun View?.blockClickable(blockTimeMilles: Long = 200) {
    this?.isEnabled = false
    Looper.myLooper()?.let {
        Handler(it).postDelayed({ this?.isEnabled = true }, blockTimeMilles)
    }
}