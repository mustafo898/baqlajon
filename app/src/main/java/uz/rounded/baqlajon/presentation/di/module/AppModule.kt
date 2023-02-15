package uz.rounded.baqlajon.presentation.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import uz.rounded.baqlajon.core.utils.SharedPreference
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        RepositoryModule::class
    ]
)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreference(application: Application): SharedPreference =
        SharedPreference.getInstance(application.applicationContext)
}