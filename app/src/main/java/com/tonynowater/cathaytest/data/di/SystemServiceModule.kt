package com.tonynowater.cathaytest.data.di

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SystemServiceModule {

    @Provides
    fun providesLocaleManager(@ApplicationContext appContext: Context): LocaleManager? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            appContext.getSystemService(LocaleManager::class.java)
        } else {
            null
        }
    }
}