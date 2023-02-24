package uz.rounded.baqlajon.core.extensions

import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
    findNavController().navigate(id, bundle)
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