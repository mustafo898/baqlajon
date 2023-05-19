package uz.rounded.baqlajon.core.utils.theme

abstract class Theme {
    abstract val id: Long
    abstract val style: Int
    abstract val name: Int
    abstract val colorPrimary: Int
    abstract val colorPrimaryDark: Int
    abstract val colorAccent: Int
    abstract val backgroundColor: Int
    abstract val backgroundLight: Int
    abstract val defTextColor: Int
    abstract val secondaryTextColor: Int
    abstract val separateTextColor: Int

    companion object {
        const val CLASSIC_THEME = 0L
        const val NIGHT_THEME = 1L
    }

}