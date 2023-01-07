package com.tonynowater.cathaytest.data.locale

interface AppLocaleProvider {
    fun getAppLocale(): String
    fun setAppLocale(localeTag: String)
}