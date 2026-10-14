package com.example.TibiaTools.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class GoldFormatterTest {

    @Test
    fun `format groups thousands`() {
        assertEquals("0", GoldFormatter.format(0, Locale.US))
        assertEquals("999", GoldFormatter.format(999, Locale.US))
        assertEquals("1,500", GoldFormatter.format(1_500, Locale.US))
        assertEquals("1,234,567", GoldFormatter.format(1_234_567, Locale.US))
    }

    @Test
    fun `format respects locale separators`() {
        assertEquals("1.500", GoldFormatter.format(1_500, Locale.GERMANY))
    }

    @Test
    fun `formatCoins appends suffix to numeric values`() {
        assertEquals("2,500 gold coins", GoldFormatter.formatCoins("2500", Locale.US))
        assertEquals("0 gold coins", GoldFormatter.formatCoins("0", Locale.US))
        assertEquals("170 gold coins", GoldFormatter.formatCoins(" 170 ", Locale.US))
    }

    @Test
    fun `formatCoins returns raw text when not numeric`() {
        assertEquals("N/A", GoldFormatter.formatCoins("N/A", Locale.US))
        assertEquals("", GoldFormatter.formatCoins("", Locale.US))
        assertEquals("12.5", GoldFormatter.formatCoins("12.5", Locale.US))
    }
}
