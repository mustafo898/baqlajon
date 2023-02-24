package uz.rounded.baqlajon

import android.app.Application
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import uz.rounded.baqlajon.core.utils.SharedPreference

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var resources: Resources
        lateinit var sharedPref: SharedPreference
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

        Hawk.init(this).build()

        sharedPref = SharedPreference.getInstance(applicationContext)

        if (sharedPref.nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        if (sharedPref.language.isEmpty()) {
            sharedPref.language = "3"
            Hawk.put("pref_lang", "ru")
        } else {
            when (sharedPref.language) {
                "1" -> Hawk.put("pref_lang", "uz")
                "2" -> Hawk.put("pref_lang", "en")
                "3" -> Hawk.put("pref_lang", "ru")
            }
        }
    }
}