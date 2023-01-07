package com.tonynowater.cathaytest.data.network_provider

import com.tonynowater.cathaytest.data.locale.LocaleToLanguageMapperImpl
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class LocaleToLanguageMapperImplTest {

    @Test
    fun mappingZhTw() {
        val localeToLanguageMapperImpl = LocaleToLanguageMapperImpl()
        val languageCode = localeToLanguageMapperImpl.mapping(Locale.TRADITIONAL_CHINESE)
        assertEquals("zh-tw", languageCode)
    }

    @Test
    fun mappingZhCn() {
        val localeToLanguageMapperImpl = LocaleToLanguageMapperImpl()
        val languageCode = localeToLanguageMapperImpl.mapping(Locale.SIMPLIFIED_CHINESE)
        assertEquals("zh-cn", languageCode)
    }

    @Test
    fun mappingEn() {
        val localeToLanguageMapperImpl = LocaleToLanguageMapperImpl()
        val languageCode = localeToLanguageMapperImpl.mapping(Locale.ENGLISH)
        assertEquals("en", languageCode)
    }

    @Test
    fun mappingJa() {
        val localeToLanguageMapperImpl = LocaleToLanguageMapperImpl()
        val languageCode = localeToLanguageMapperImpl.mapping(Locale.JAPAN)
        assertEquals("ja", languageCode)
    }

    @Test
    fun mappingKo() {
        val localeToLanguageMapperImpl = LocaleToLanguageMapperImpl()
        val languageCode = localeToLanguageMapperImpl.mapping(Locale.KOREAN)
        assertEquals("ko", languageCode)
    }

    @Test
    fun mappingEs() {
        val localeToLanguageMapperImpl = LocaleToLanguageMapperImpl()
        val languageCode = localeToLanguageMapperImpl.mapping(Locale.forLanguageTag("es"))
        assertEquals("es", languageCode)
    }

    @Test
    fun mappingId() {
        val localeToLanguageMapperImpl = LocaleToLanguageMapperImpl()
        val languageCode = localeToLanguageMapperImpl.mapping(Locale.forLanguageTag("id"))
        assertEquals("id", languageCode)
    }

    @Test
    fun mappingTh() {
        val localeToLanguageMapperImpl = LocaleToLanguageMapperImpl()
        val languageCode = localeToLanguageMapperImpl.mapping(Locale.forLanguageTag("th"))
        assertEquals("th", languageCode)
    }

    @Test
    fun mappingVi() {
        val localeToLanguageMapperImpl = LocaleToLanguageMapperImpl()
        val languageCode = localeToLanguageMapperImpl.mapping(Locale.forLanguageTag("vi"))
        assertEquals("vi", languageCode)
    }
}