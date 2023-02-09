package uz.rounded.baqlajon

import android.app.Application
import android.content.res.Resources
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var resources: Resources
        const val LANGUAGE_UZBEK = "uz"
        const val LANGUAGE_UZBEK_COUNTRY = "UZ"
        const val LANGUAGE_RUSSIAN = "ru"
        const val LANGUAGE_RUSSIAN_COUNTRY = "RU"
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_ENGLISH_COUNTRY = "EN"
    }

    override fun onCreate() {
        super.onCreate()
        Companion.resources = resources

    }
}