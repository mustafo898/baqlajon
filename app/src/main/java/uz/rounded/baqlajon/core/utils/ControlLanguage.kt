package uz.rounded.baqlajon.core.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.orhanobut.hawk.Hawk
import java.util.*

object ControlLanguage {
    fun setLocale(context: Context): Context? {
        return updateResources(context, getLanguagePref(context))
    }

    fun setNewLocale(context: Context, language: String): Context? {
        setLanguagePref(context, language)
        return updateResources(context, language)
    }

    private fun getLanguagePref(context: Context): String {
        return Hawk.get("pref_lang", "en")
    }

    private fun setLanguagePref(context: Context, localeKey: String) {
        Hawk.put("pref_lang", localeKey)
    }

    private fun updateResources(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }

    fun getLocale(resources: Resources): Locale? {
        val configuration = resources.configuration
        return configuration.locales.get(0)
    }
}
