package com.tonynowater.cathaytest.feature.attraction_detail

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
class AttractionDetailViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val tripInfoRepository: TripInfoRepository,
) : ViewModel() {

    private val _uiState = MutableLiveData<AttractionDetailUiState>()
    val uiState: LiveData<AttractionDetailUiState> get() = _uiState

    fun fetchAttractionDetail(id: Int) {
        viewModelScope.launch {
            _uiState.value = AttractionDetailUiState.Loading
            try {
                val attractionDetail =
                    tripInfoRepository.getAttractionById(id = id)
                _uiState.value = AttractionDetailUiState.Success(
                    data = AttractionUiModel.map(attractionDetail)
                )
            } catch (exception: Exception) {
                _uiState.value =
                    AttractionDetailUiState.Error(
                        errorMessage = exception.message ?: appContext.getString(
                            R.string.unkown_error
                        )
                    )
            }
        }
    }
}