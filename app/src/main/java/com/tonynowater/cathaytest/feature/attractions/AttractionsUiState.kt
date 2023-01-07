package com.tonynowater.cathaytest.feature.attractions

import com.tonynowater.cathaytest.feature.attraction_detail.AttractionDetailUiState
import com.tonynowater.cathaytest.feature.model.AttractionUiModel

sealed interface AttractionsUiState {
    object Loading : AttractionsUiState

    data class Attractions(
        val attractions: List<AttractionUiModel>
    ) : AttractionsUiState

    data class Error(val errorMessage: String) : AttractionsUiState
}


