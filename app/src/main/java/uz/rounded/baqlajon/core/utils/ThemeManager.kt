package uz.rounded.baqlajon.core.utils

import android.app.Application
import uz.rounded.baqlajon.core.utils.theme.ClassicTheme
import uz.rounded.baqlajon.core.utils.theme.NightTheme
import uz.rounded.baqlajon.core.utils.theme.Theme
import javax.inject.Inject

class ThemeManager @Inject constructor() {

    @Inject
    lateinit var prefs: SharedPreference

    @Inject
    lateinit var app: Application

    var themes: ArrayList<Theme> = ArrayList()

    init {
        themes.add(ClassicTheme())
        themes.add(NightTheme())
    }

    var currentTheme: Theme
        get() = findThemeById(prefs.get(prefs.theme, getDefaultTheme().id))
        set(value) {
            prefs.save(prefs.theme, value.id)
            app.setTheme(value.style)
        }

    private fun findThemeById(id: Long): Theme {
        themes.forEach {
            if (it.id == id)
                return it
        }
        return getDefaultTheme()
    }

    fun getDefaultTheme(): Theme {
        return themes[0]
    }

    fun getAllThemes() = themes
}