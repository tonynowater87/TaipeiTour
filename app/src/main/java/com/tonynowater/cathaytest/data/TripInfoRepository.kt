package com.tonynowater.cathaytest.data

import com.tonynowater.cathaytest.data.network_provider.model.NetworkAttractions

interface TripInfoRepository {
    suspend fun getAttractions(): NetworkAttractions
    suspend fun getAttractionById(id: Int): NetworkAttractions.Data
}