package uz.rounded.baqlajon.core.utils.theme

import uz.rounded.baqlajon.R

class ClassicTheme : Theme() {
    override val id: Long
        get() = CLASSIC_THEME
    override val name: Int
        get() = R.string.classic
    override val style: Int
        get() = R.style.ClassicTheme
    override val colorPrimary: Int
        get() = R.color.cl_color_primary
    override val colorPrimaryDark: Int
        get() = R.color.cl_color_primary_dark
    override val colorAccent: Int
        get() = R.color.cl_color_accent
    override val backgroundColor: Int
        get() = R.color.cl_background
    override val backgroundLight: Int
        get() = R.color.cl_background_light
    override val defTextColor: Int
        get() = R.color.cl_def_text_color
    override val secondaryTextColor: Int
        get() = R.color.cl_secondary_text_color
    override val separateTextColor: Int
        get() = R.color.cl_separate_text_color
}