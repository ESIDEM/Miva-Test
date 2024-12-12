package com.esidem.mivatest.di

import android.content.Context
import com.esidem.mivatest.repository.DownloadManager
import com.esidem.mivatest.repository.DownloadManagerImpl
import com.esidem.mivatest.network.MivaService
import com.esidem.mivatest.utils.PlayerUtil
import com.russhwolf.settings.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val CHAPTERS_URL = "https://private-8af5e5-mobileappconsultant.apiary-mock.com/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }
        okHttpClient.callTimeout(60, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClient.readTimeout(60, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(60, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }


    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CHAPTERS_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideMivaService(retrofit: Retrofit): MivaService = retrofit.create(MivaService::class.java)

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    @Singleton
    @Provides
    fun provideSettings(): Settings = Settings()

    @Singleton
    @Provides
    fun providePlayerUtil(): PlayerUtil = PlayerUtil()

    @Singleton
    @Provides
    fun provideDownloadManager(context: Context): DownloadManager = DownloadManagerImpl(context)

}