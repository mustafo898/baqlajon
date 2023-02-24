package uz.rounded.baqlajon.core.utils

import com.orhanobut.hawk.Hawk

class ControlLanguage {

    companion object {
        fun setLocale(mContext: android.content.Context): android.content.Context? {
            return updateResources(mContext, getLanguagePref(mContext))
        }


        fun setNewLocale(
            mContext: android.content.Context,
            language: kotlin.String
        ): android.content.Context? {
            setLanguagePref(mContext, language)
            return updateResources(mContext, language)
        }

        private fun getLanguagePref(mContext: android.content.Context?): kotlin.String {
            return Hawk.get<kotlin.String>("pref_lang", "en")
        }

        private fun setLanguagePref(mContext: android.content.Context, localeKey: kotlin.String) {
            Hawk.get<kotlin.String>("pref_lang", localeKey)
        }

        private fun updateResources(
            context: android.content.Context,
            language: kotlin.String
        ): android.content.Context? {
            var context: android.content.Context = context
            val locale: java.util.Locale = java.util.Locale(language)
            java.util.Locale.setDefault(locale)
            val res: android.content.res.Resources = context.resources
            val config: android.content.res.Configuration =
                android.content.res.Configuration(res.configuration)
            config.setLocale(locale)
            context = context.createConfigurationContext(config)
            res.updateConfiguration(config, res.displayMetrics)
            return context
        }

        fun getLocale(res: android.content.res.Resources): java.util.Locale? {
            val config: android.content.res.Configuration = res.configuration
            return config.locales.get(0)
        }


    }
}