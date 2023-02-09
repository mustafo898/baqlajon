package uz.rounded.baqlajon.core.extensions

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import uz.rounded.baqlajon.domain.model.UserModel

fun Fragment.popBackStack() {
    findNavController().popBackStack()
}

fun Fragment.navigate(id: Int) {
    findNavController().navigate(id)
}

fun Fragment.navigateWithArgs(id: Int, bundle: Bundle) {
    findNavController().navigate(id, bundle)
}

fun objectToJson(data: UserModel): String {
    return Gson().toJson(data)
}

fun jsonToObject(data: String?): UserModel {
    return Gson().fromJson(data ?: "null", UserModel::class.java)
}