package uz.rounded.baqlajon.core.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import uz.rounded.baqlajon.domain.model.UserResponseModel

fun Fragment.popBackStack() {
    findNavController().popBackStack()
}

fun Fragment.navigate(id: Int) {
    findNavController().navigate(id)
}

fun Fragment.navigateWithArgs(id: Int, bundle: Bundle) {
    findNavController().navigate(id, bundle)
}

fun objectToJson(data: UserResponseModel): String {
    return Gson().toJson(data)
}

fun jsonToObject(data: String?): UserResponseModel {
    return Gson().fromJson(data ?: "null", UserResponseModel::class.java)
}