package uz.rounded.baqlajon.core.utils

import java.lang.Exception

sealed class MyResponse<out T:Any> {
    data class Success<out T:Any>(val data:T):MyResponse<T>()
    data class Error(val exception: Exception):MyResponse<Nothing>()
}