package com.tonynowater.cathaytest.data.locale

import java.util.Locale

interface LocaleToLanguageMapper {
    fun mapping(locale: Locale): String
}