package com.tonynowater.cathaytest.data.locale

import java.util.Locale
import javax.inject.Inject

class LocaleToLanguageMapperImpl @Inject constructor(): LocaleToLanguageMapper {

    override fun mapping(locale: Locale): String {
        return when (locale.language) {
            "zh" -> "${locale.language}-${locale.country}".lowercase()
            "en" -> locale.language.lowercase()
            "ja" -> locale.language.lowercase()
            "ko" -> locale.language.lowercase()
            "es" -> locale.language.lowercase()
            "in" -> locale.toLanguageTag() // id
            "th" -> locale.language.lowercase()
            "vi" -> locale.language.lowercase()
            else -> throw IllegalArgumentException("unexpected locale: $locale")
        }
    }
}