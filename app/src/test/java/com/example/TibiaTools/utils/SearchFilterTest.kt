package com.example.TibiaTools.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.util.Locale

class SearchFilterTest {

    @Test
    fun `defaults are empty`() {
        val filter = SearchFilter()
        assertEquals("", filter.world)
        assertEquals("", filter.category)
        assertEquals("", filter.vocation)
        assertEquals("", filter.city)
        assertEquals("", filter.categoryQuery)
    }

    @Test
    fun `categoryQuery removes spaces and lowercases`() {
        assertEquals("magiclevel", SearchFilter(category = "Magic Level").categoryQuery)
        assertEquals("goshnarstaint", SearchFilter(category = "Goshnar's Taint".replace("'", "")).categoryQuery)
        assertEquals("experience", SearchFilter(category = "Experience").categoryQuery)
    }

    @Test
    fun `categoryQuery is locale independent`() {
        // Con Locale turco, "I".lowercase(Locale.forLanguageTag("tr")) da "ı"; con Locale.ROOT siempre da "i".
        val original = Locale.getDefault()
        try {
            Locale.setDefault(Locale.forLanguageTag("tr-TR"))
            assertEquals("distancefighting", SearchFilter(category = "DISTANCE FIGHTING").categoryQuery)
        } finally {
            Locale.setDefault(original)
        }
    }

    @Test
    fun `equality is structural`() {
        assertEquals(SearchFilter(world = "Antica", city = "Thais"), SearchFilter(world = "Antica", city = "Thais"))
        assertNotEquals(SearchFilter(world = "Antica"), SearchFilter(world = "Secura"))
    }
}
