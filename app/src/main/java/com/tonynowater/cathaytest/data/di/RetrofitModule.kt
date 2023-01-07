package com.tonynowater.cathaytest.data.di

import com.tonynowater.cathaytest.BuildConfig
import com.tonynowater.cathaytest.data.API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HeaderJsonInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HeaderXmlInterceptorOkHttpClient


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @HeaderJsonInterceptorOkHttpClient
    @Provides
    fun providesJsonHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val requestOut = chain.request().newBuilder().apply {
                header("accept", "application/json")
            }.build()
            chain.proceed(requestOut)
        }
    }

    @HeaderXmlInterceptorOkHttpClient
    @Provides
    fun providesXmlHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val requestOut = chain.request().newBuilder().apply {
                header("accept", "application/xml")
            }.build()
            chain.proceed(requestOut)
        }
    }

    @Provides
    fun providesRetrofit(@HeaderJsonInterceptorOkHttpClient headerInterceptor: Interceptor): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(API_BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .apply {
                        addInterceptor(headerInterceptor)
                        if (BuildConfig.DEBUG) {
                            addInterceptor(HttpLoggingInterceptor().apply {
                                setLevel(
                                    HttpLoggingInterceptor.Level.BODY
                                )
                            })
                        }
                    }
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}