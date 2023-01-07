package com.tonynowater.cathaytest.feature.language

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tonynowater.cathaytest.data.locale.AppLocaleProvider
import com.tonynowater.cathaytest.data.locale.LocaleToLanguageMapper
import com.tonynowater.cathaytest.feature.model.LanguagePickerUiModel
import com.tonynowater.cathaytest.utils.extensions.getLocaleListFromXml
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LanguagePickerViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val localeProvider: AppLocaleProvider,
    private val languageMapper: LocaleToLanguageMapper
) : ViewModel() {


    private val _uiState = MutableLiveData<List<LanguagePickerUiModel>>()
    val uiState: LiveData<List<LanguagePickerUiModel>> get() = _uiState

    fun loadAppLocales() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val languagePickerUiModels = mutableListOf<LanguagePickerUiModel>()
            val applicationSystemLocales = appContext.getLocaleListFromXml()
            for (index in 0 until applicationSystemLocales.size()) {
                val appLocale = applicationSystemLocales.get(index)
                languagePickerUiModels.add(
                    LanguagePickerUiModel(
                        displayName = appLocale!!.displayName,
                        languageTag = languageMapper.mapping(appLocale),
                        preferred = localeProvider.getAppLocale() == languageMapper.mapping(
                            appLocale
                        )
                    )
                )
            }
            _uiState.value = languagePickerUiModels
        }
    }

    fun changeLocale(localeTag: String) {
        localeProvider.setAppLocale(localeTag)
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(localeTag)
        // Call this on the main thread as it may require Activity.restart()
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}