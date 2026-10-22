package com.example.TibiaTools.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream

class RawResourceReaderTest {

    private fun streamOf(text: String) = ByteArrayInputStream(text.toByteArray(Charsets.UTF_8))

    @Test
    fun `readLines returns each line in order`() {
        val lines = RawResourceReader.readLines(streamOf("Seleccione una vocación\nKnight\nPaladin\n"))
        assertEquals(listOf("Seleccione una vocación", "Knight", "Paladin"), lines)
    }

    @Test
    fun `readLines handles CRLF line endings`() {
        assertEquals(listOf("Thais", "Venore"), RawResourceReader.readLines(streamOf("Thais\r\nVenore")))
    }

    @Test
    fun `readLines of empty stream is empty`() {
        assertTrue(RawResourceReader.readLines(streamOf("")).isEmpty())
    }

    @Test
    fun `readLines closes the stream`() {
        val stream = CloseTrackingStream(streamOf("a\nb"))
        RawResourceReader.readLines(stream)
        assertTrue(stream.closed)
    }

    @Test
    fun `readLines returns empty list and closes on IOException`() {
        val stream = FailingStream()
        assertTrue(RawResourceReader.readLines(stream).isEmpty())
        assertTrue(stream.closed)
    }

    @Test
    fun `readText concatenates lines without separator`() {
        assertEquals("abc", RawResourceReader.readText(streamOf("a\nb\nc")))
    }

    @Test
    fun `readText skips header lines`() {
        assertEquals("bc", RawResourceReader.readText(streamOf("header\nb\nc"), skipLines = 1))
        assertEquals("", RawResourceReader.readText(streamOf("a\nb"), skipLines = 5))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `readText rejects negative skipLines`() {
        RawResourceReader.readText(streamOf("a"), skipLines = -1)
    }

    private class CloseTrackingStream(private val delegate: InputStream) : InputStream() {
        var closed = false
        override fun read(): Int = delegate.read()
        override fun read(b: ByteArray, off: Int, len: Int): Int = delegate.read(b, off, len)
        override fun close() { closed = true; delegate.close() }
    }

    private class FailingStream : InputStream() {
        var closed = false
        override fun read(): Int = throw IOException("boom")
        override fun read(b: ByteArray, off: Int, len: Int): Int = throw IOException("boom")
        override fun close() { closed = true }
    }
}
