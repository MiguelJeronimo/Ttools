package com.example.TibiaTools.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RootDetectorTest {

    @Test
    fun `rooted when superuser apk exists and su runs`() {
        assertTrue(RootDetector(fileExists = { true }, canRunSu = { true }).isRooted())
    }

    @Test
    fun `not rooted when superuser apk is missing`() {
        var suCalled = false
        val detector = RootDetector(fileExists = { false }, canRunSu = { suCalled = true; true })

        assertFalse(detector.isRooted())
        assertFalse("No debería ejecutar su si falta el APK", suCalled)
    }

    @Test
    fun `not rooted when su cannot run`() {
        assertFalse(RootDetector(fileExists = { true }, canRunSu = { false }).isRooted())
    }

    @Test
    fun `checks the expected superuser path`() {
        var checkedPath: String? = null
        RootDetector(fileExists = { checkedPath = it; false }, canRunSu = { false }).isRooted()
        assertEquals(RootDetector.SUPERUSER_APK_PATH, checkedPath)
    }

    @Test
    fun `exceptions are treated as not rooted`() {
        assertFalse(RootDetector(fileExists = { throw SecurityException() }, canRunSu = { true }).isRooted())
        assertFalse(RootDetector(fileExists = { true }, canRunSu = { throw RuntimeException() }).isRooted())
    }
}
