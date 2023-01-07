package com.tonynowater.cathaytest.data.network_provider

import com.tonynowater.cathaytest.data.network_provider.api.AttractionsApi
import com.tonynowater.cathaytest.data.network_provider.model.NetworkAttractions
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkAttractionsProviderImpl @Inject constructor(private val retrofit: Retrofit) :
    NetworkAttractionsProvider {

    private val attractionsApi = retrofit.create(AttractionsApi::class.java)

    override suspend fun getAttractions(language: String): NetworkAttractions {
        return attractionsApi.getAttractions(language)
    }
}