package com.tonynowater.cathaytest.data

import com.tonynowater.cathaytest.data.locale.AppLocaleProvider
import com.tonynowater.cathaytest.data.network_provider.MemoryAttractionsProvider
import com.tonynowater.cathaytest.data.network_provider.NetworkAttractionsProvider
import com.tonynowater.cathaytest.data.network_provider.model.NetworkAttractions
import javax.inject.Inject

class TripInfoRepositoryImpl @Inject constructor(
    private val memoryAttractionsProvider: MemoryAttractionsProvider,
    private val networkAttractionsProvider: NetworkAttractionsProvider,
    private val appLocaleProvider: AppLocaleProvider
) : TripInfoRepository {

    override suspend fun getAttractions(): NetworkAttractions {
        val language = appLocaleProvider.getAppLocale()
        val cachedData = memoryAttractionsProvider.getAttractions(language)
        if (cachedData != null) {
            return cachedData
        }
        val attractions = networkAttractionsProvider.getAttractions(language)
        memoryAttractionsProvider.setAttractions(language, attractions)
        return attractions
    }

    override suspend fun getAttractionById(id: Int): NetworkAttractions.Data {
        val attractions = getAttractions()
        return attractions.data.first { it.id == id }
    }
}