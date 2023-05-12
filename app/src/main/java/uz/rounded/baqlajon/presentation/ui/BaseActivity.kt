package uz.rounded.baqlajon.presentation.ui

import android.os.Bundle
import android.view.Display.FLAG_SECURE
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity(@LayoutRes val layoutID: Int) : DaggerAppCompatActivity() {

    open val canChangeTheme: Boolean = true

//    @Inject
//    lateinit var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (canChangeTheme) {
            window.setFlags(FLAG_SECURE, FLAG_SECURE)
//            setTheme(themeManager.currentTheme.style)
        }
        setContentView(layoutID)
        onAfterCreate()
    }

    abstract fun onAfterCreate()

}