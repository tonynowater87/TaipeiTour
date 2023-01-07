package com.tonynowater.cathaytest.data.network_provider

import com.tonynowater.cathaytest.data.network_provider.model.NetworkAttractions

interface MemoryAttractionsProvider {
    fun getAttractions(language: String): NetworkAttractions?
    fun setAttractions(language: String, networkAttractions: NetworkAttractions)
}