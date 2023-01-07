package com.tonynowater.cathaytest.feature.attraction_detail

import com.tonynowater.cathaytest.feature.model.AttractionUiModel

sealed interface AttractionDetailUiState {
    object Loading : AttractionDetailUiState

    data class Success(
        val data: AttractionUiModel
    ) : AttractionDetailUiState

    data class Error(val errorMessage: String) : AttractionDetailUiState
}


