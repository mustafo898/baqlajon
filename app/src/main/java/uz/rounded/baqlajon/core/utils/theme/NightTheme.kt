package uz.rounded.baqlajon.core.utils.theme

import uz.rounded.baqlajon.R

class NightTheme : Theme() {
    override val id: Long
        get() = NIGHT_THEME
    override val name: Int
        get() = R.string.night_mode
    override val style: Int
        get() = R.style.NightTheme
    override val colorPrimary: Int
        get() = R.color.n_color_primary
    override val colorPrimaryDark: Int
        get() = R.color.n_color_primary_dark
    override val colorAccent: Int
        get() = R.color.n_color_accent
    override val backgroundColor: Int
        get() = R.color.n_background
    override val backgroundLight: Int
        get() = R.color.n_background_light
    override val defTextColor: Int
        get() = R.color.n_def_text_color
    override val secondaryTextColor: Int
        get() = R.color.n_secondary_text_color
    override val separateTextColor: Int
        get() = R.color.n_separate_text_color
}