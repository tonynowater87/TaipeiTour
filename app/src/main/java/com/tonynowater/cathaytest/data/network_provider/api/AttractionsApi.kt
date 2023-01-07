package com.tonynowater.cathaytest.data.network_provider.api

import com.tonynowater.cathaytest.data.network_provider.model.NetworkAttractions
import retrofit2.http.GET
import retrofit2.http.Path

interface AttractionsApi {

    // Attractions 遊憩景點 https://www.travel.taipei/open-api/en/Attractions/All?page=1
    @GET("{languageCode}/Attractions/All?page=1")
    suspend fun getAttractions(@Path("languageCode") language: String): NetworkAttractions
}