package com.tonynowater.cathaytest.feature.model

import com.tonynowater.cathaytest.data.network_provider.model.NetworkAttractions

data class AttractionUiModel(
    val id: Int,
    val imageUrl: String?,
    val name: String,
    val introduction: String,
    val address: String,
    val openTime: String,
    val lastUpdatedDateString: String,
    val url: String
) {
    companion object {
        fun map(networkAttractionData: NetworkAttractions.Data): AttractionUiModel {
            return AttractionUiModel(
                id = networkAttractionData.id,
                imageUrl = networkAttractionData.images.firstOrNull()?.src,
                name = networkAttractionData.name,
                introduction = networkAttractionData.introduction,
                address = networkAttractionData.address,
                openTime = networkAttractionData.openTime,
                lastUpdatedDateString = networkAttractionData.modified,
                url = networkAttractionData.url
            )
        }
    }
}