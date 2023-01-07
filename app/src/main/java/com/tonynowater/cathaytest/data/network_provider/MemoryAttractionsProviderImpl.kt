package com.tonynowater.cathaytest.data.network_provider

import com.tonynowater.cathaytest.data.network_provider.model.NetworkAttractions
import javax.inject.Inject

class MemoryAttractionsProviderImpl @Inject constructor() : MemoryAttractionsProvider {

    private val cachedData = mutableMapOf<String, NetworkAttractions>()

    override fun getAttractions(language: String): NetworkAttractions? {
        return cachedData[language]
    }

    override fun setAttractions(language: String, networkAttractions: NetworkAttractions) {
        cachedData[language] = networkAttractions
    }
}