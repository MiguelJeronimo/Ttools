package com.example.TibiaTools.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.util.Locale
import java.util.TimeZone

class DateFormatterTest {

    private val utc = TimeZone.getTimeZone("UTC")
    private val mexicoCity = TimeZone.getTimeZone("America/Mexico_City")

    @Test
    fun `formats UTC date in UTC`() {
        assertEquals("25/09/2026 18:30", DateFormatter.format("2026-09-25T18:30:00Z", utc))
    }

    @Test
    fun `converts UTC to target time zone`() {
        // Mexico City es UTC-6 (sin horario de verano desde 2022)
        assertEquals("25/09/2026 12:30", DateFormatter.format("2026-09-25T18:30:00Z", mexicoCity))
    }

    @Test
    fun `conversion can change the day`() {
        assertEquals("31/12/2025 18:00", DateFormatter.format("2026-01-01T00:00:00Z", mexicoCity))
    }

    @Test
    fun `supports custom display pattern and locale`() {
        assertEquals(
            "Sep 25, 2026",
            DateFormatter.format("2026-09-25T18:30:00Z", utc, displayPattern = "MMM d, yyyy", locale = Locale.US)
        )
    }

    @Test
    fun `returns null for null or blank input`() {
        assertNull(DateFormatter.format(null))
        assertNull(DateFormatter.format(""))
        assertNull(DateFormatter.format("   "))
    }

    @Test
    fun `returns null for malformed input`() {
        assertNull(DateFormatter.format("25/09/2026"))
        assertNull(DateFormatter.format("2026-09-25"))
        assertNull(DateFormatter.format("not a date"))
    }

    @Test
    fun `returns null for impossible dates instead of rolling over`() {
        assertNull(DateFormatter.format("2026-02-30T10:00:00Z", utc))
    }

    @Test
    fun `a failed parse does not leak the previous result`() {
        assertEquals("25/09/2026 18:30", DateFormatter.format("2026-09-25T18:30:00Z", utc))
        assertNull(DateFormatter.format("invalid", utc))
    }
}
