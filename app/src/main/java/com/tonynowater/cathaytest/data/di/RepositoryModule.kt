package com.tonynowater.cathaytest.data.di

import com.tonynowater.cathaytest.data.TripInfoRepository
import com.tonynowater.cathaytest.data.TripInfoRepositoryImpl
import com.tonynowater.cathaytest.data.locale.AppLocaleProvider
import com.tonynowater.cathaytest.data.locale.AppLocaleProviderImpl
import com.tonynowater.cathaytest.data.locale.LocaleToLanguageMapper
import com.tonynowater.cathaytest.data.locale.LocaleToLanguageMapperImpl
import com.tonynowater.cathaytest.data.network_provider.MemoryAttractionsProvider
import com.tonynowater.cathaytest.data.network_provider.MemoryAttractionsProviderImpl
import com.tonynowater.cathaytest.data.network_provider.NetworkAttractionsProvider
import com.tonynowater.cathaytest.data.network_provider.NetworkAttractionsProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsMemoryAttractionsProvider(
        memoryAttractionsProviderImpl: MemoryAttractionsProviderImpl
    ): MemoryAttractionsProvider

    @Binds
    abstract fun bindsNetworkAttractionsProvider(
        networkAttractionsProviderImpl: NetworkAttractionsProviderImpl
    ): NetworkAttractionsProvider

    @Singleton
    @Binds
    abstract fun bindsLocaleToLanguageMapper(
        localeToLanguageMapperImpl: LocaleToLanguageMapperImpl
    ): LocaleToLanguageMapper

    @Singleton
    @Binds
    abstract fun bindsAppLocaleProvider(
        appLocaleProviderImpl: AppLocaleProviderImpl
    ): AppLocaleProvider?

    @Singleton
    @Binds
    abstract fun bindsTripInfoRepository(
        tripInfoRepositoryImpl: TripInfoRepositoryImpl
    ): TripInfoRepository
}