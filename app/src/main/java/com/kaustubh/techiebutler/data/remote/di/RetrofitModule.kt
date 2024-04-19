package com.kaustubh.techiebutler.data.remote.di

import com.kaustubh.techiebutler.data.NetworkConstants.BASE_URL
import com.kaustubh.techiebutler.data.remote.services.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideApiServices(): ApiServices {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(getClient())
            .build().create(ApiServices::class.java)
    }

    private fun getClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder.connectTimeout(1, TimeUnit.MINUTES)
        clientBuilder.readTimeout(1, TimeUnit.MINUTES)
        clientBuilder.writeTimeout(1, TimeUnit.MINUTES)

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)

        return clientBuilder.build()
    }

}