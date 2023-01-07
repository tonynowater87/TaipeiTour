package com.tonynowater.cathaytest.data.network_provider

import com.tonynowater.cathaytest.data.network_provider.model.NetworkAttractions

interface NetworkAttractionsProvider {
    suspend fun getAttractions(language: String): NetworkAttractions
}