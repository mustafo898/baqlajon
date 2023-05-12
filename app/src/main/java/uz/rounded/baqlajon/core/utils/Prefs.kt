package org.ost.avtostart.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class Prefs @Inject constructor(app: Context) {

    private val prefsName: String = "AvtoPref"
    private var prefs: SharedPreferences

    val token = "token"
    val theme = "theme"
    val language = "language"
    val scanData = "scanData"
    val fullname = "fullname"
    val eduType = "eduType"
    val region = "region"
    val startDate = "startDate"
    val endDate = "endDate"
    val payment = "payment"
    val fontSize = "fontSize"
    val searchContent = "search"
    val password = "password"
    val autoDownload = "autoDownload"
    val autoDownloadVideo = "autodownloadvideo"
    val autoDownloadFiles = "autodownloadfiles"
    val autoDownloadAudio = "autodownloadaudio"
    val version = "version"
    val pointBall = "pointBall"
    val newLessonUpdatedTime = "newLessonUpdatedTime"
    val countSubjects = "countofsubjects"
    val useFingerprint = "useFingerPrint"
    val passwordTime = "passwordTime"
    val timeExit = "ExitTime"
    val timeOpenApp = "Open App"
    val notification = "notification"
    val vibration = "vibration"
    val isUseProxy = "isUseProxy"
    val server = "server"
    val port = "port"
    val loginProxy = "loginProxy"
    val passwordProxy = "passwordProxy"

    init {
        prefs = app.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        Log.d("TTT", prefsName)
    }

    fun save(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    fun save(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun save(key: String, value: Float) {
        prefs.edit().putFloat(key, value).apply()
    }

    fun save(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun save(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }

    fun get(key: String, defValue: Int) = prefs.getInt(key, defValue)

    fun get(key: String, defValue: String) = prefs.getString(key, defValue) ?: ""

    fun get(key: String, defValue: Float) = prefs.getFloat(key, defValue)

    fun get(key: String, defValue: Boolean) = prefs.getBoolean(key, defValue)

    fun get(key: String, defValue: Long) = prefs.getLong(key, defValue)

    fun clear() {
        prefs.edit().clear().apply()
    }
}
