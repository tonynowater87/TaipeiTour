package com.tonynowater.cathaytest.data.locale

import android.app.LocaleManager
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Locale
import javax.inject.Inject

class AppLocaleProviderImpl @Inject constructor(
    private val localeManager: LocaleManager?,
    private val localeToLanguageMapper: LocaleToLanguageMapper
) : AppLocaleProvider {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val defaultLocale = if (localeManager!!.applicationLocales.isEmpty) {
        Locale.getDefault()
    } else {
        localeManager.applicationLocales[0]
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private var languageCode = localeToLanguageMapper.mapping(defaultLocale)

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun getAppLocale(): String {
        return languageCode
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun setAppLocale(localeTag: String) {
        languageCode = localeTag
    }
}