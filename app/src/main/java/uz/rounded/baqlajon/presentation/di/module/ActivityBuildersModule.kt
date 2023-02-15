package uz.rounded.baqlajon.presentation.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import uz.rounded.baqlajon.presentation.MainActivity

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [ViewModelsModule::class, MainFragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}