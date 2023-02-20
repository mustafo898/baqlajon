package uz.rounded.baqlajon.presentation.di.module

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
import uz.rounded.baqlajon.data.remote.AuthApiService
import uz.rounded.baqlajon.data.remote.MainApiService
import uz.rounded.baqlajon.data.repository.AuthRepositoryImpl
import uz.rounded.baqlajon.data.repository.MainRepositoryImpl
import uz.rounded.baqlajon.domain.repository.AuthRepository
import uz.rounded.baqlajon.domain.repository.MainRepository
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
                    .addHeader(
                        "Authorization",
                        "Bearer ${sharedPreference.token}"
                    )
                    .addHeader("lang", sharedPreference.lang)
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

    @Provides
    @Singleton
    fun provideAuthRepository(authApiService: AuthApiService): AuthRepository {
        return AuthRepositoryImpl(authApiService)
    }

    @Provides
    @Singleton
    fun provideMainRepository(mainApiService: MainApiService): MainRepository {
        return MainRepositoryImpl(mainApiService)
    }

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Singleton
    @Provides
    fun provideMainService(retrofit: Retrofit): MainApiService =
        retrofit.create(MainApiService::class.java)
}