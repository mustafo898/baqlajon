package uz.rounded.baqlajon.core.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import uz.rounded.baqlajon.domain.model.main.course.UserModel

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