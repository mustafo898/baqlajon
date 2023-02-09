package uz.rounded.baqlajon.presentation.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getString
import uz.rounded.baqlajon.core.utils.SharedPreference
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideEncryptedSharedPreference(
        @ApplicationContext context: Context
    ): SharedPreference =
        SharedPreference.getInstance(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        sharedPreference: SharedPreference
    ): OkHttpClient {
        val chuckInterceptor = ChuckerInterceptor.Builder(context)
            .maxContentLength(500_000L)
            .alwaysReadResponseBody(true)
            .build()

        val builder = OkHttpClient.Builder()
            .addInterceptor(chuckInterceptor)
            .addNetworkInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${sharedPreference.token}")
                    .addHeader("language", sharedPreference.lang)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(request)
            })
            .build()
        return builder
    }


    @Singleton
    @Provides
    fun provideRetrofit(
        gsonGsonConverterFactory: GsonConverterFactory,
        builder: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getString(R.string.base_url))
            .client(builder)
            .addConverterFactory(gsonGsonConverterFactory)
            .build()
    }
}