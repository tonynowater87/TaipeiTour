package com.tonynowater.cathaytest.feature.attractions

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonynowater.cathaytest.R
import com.tonynowater.cathaytest.feature.model.AttractionUiModel
import com.tonynowater.cathaytest.data.TripInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val tripInfoRepository: TripInfoRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<AttractionsUiState>()
    val uiState: LiveData<AttractionsUiState> get() = _uiState

    fun fetchAttractions() {
        viewModelScope.launch {
            _uiState.value = AttractionsUiState.Loading
            try {
                val attractions = tripInfoRepository.getAttractions()
                _uiState.value = AttractionsUiState.Attractions(
                    attractions = attractions.data.map {
                        AttractionUiModel.map(it)
                    }
                )
            } catch (exception: Exception) {
                _uiState.value = AttractionsUiState.Error(
                    errorMessage = exception.message ?: appContext.getString(
                        R.string.unkown_error
                    )
                )
            }
        }
    }

}