package com.example.TibiaTools.operations

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class StaminaCalculatorTest {

    // region parse
    @Test
    fun `parse accepts H mm and HH mm`() {
        assertEquals(StaminaTime(9, 5), StaminaCalculator.parse("9:05"))
        assertEquals(StaminaTime(39, 30), StaminaCalculator.parse("39:30"))
        assertEquals(StaminaTime(42, 0), StaminaCalculator.parse("42:00"))
    }

    @Test
    fun `parse accepts zero hours`() {
        assertEquals(StaminaTime(0, 30), StaminaCalculator.parse("0:30"))
    }

    @Test
    fun `parse trims whitespace`() {
        assertEquals(StaminaTime(10, 0), StaminaCalculator.parse("  10:00 "))
    }

    @Test
    fun `parse rejects values above 42 hours`() {
        assertNull(StaminaCalculator.parse("42:01"))
        assertNull(StaminaCalculator.parse("42:59"))
        assertNull(StaminaCalculator.parse("43:00"))
    }

    @Test
    fun `parse rejects malformed input`() {
        listOf("", "abc", "10", "10:", ":30", "10:60", "10:5", "100:00", "-1:00", "10.30", "10:30:00")
            .forEach { assertNull("'$it' debería ser inválido", StaminaCalculator.parse(it)) }
    }
    // endregion

    // region timeToFullStamina
    @Test
    fun `time to full stamina from 39 30`() {
        // Faltan 150 min → 150 × 6 + 10 = 910 min = 15:10
        assertEquals(StaminaTime(15, 10), StaminaCalculator.timeToFullStamina(StaminaTime(39, 30)))
    }

    @Test
    fun `time to full stamina from one minute below max`() {
        assertEquals(StaminaTime(0, 16), StaminaCalculator.timeToFullStamina(StaminaTime(41, 59)))
    }

    @Test
    fun `time to full stamina from zero`() {
        // 2520 × 6 + 10 = 15130 min = 252:10
        assertEquals(StaminaTime(252, 10), StaminaCalculator.timeToFullStamina(StaminaTime(0, 0)))
    }

    @Test
    fun `full stamina needs no offline time`() {
        assertEquals(StaminaTime(0, 0), StaminaCalculator.timeToFullStamina(StaminaTime(42, 0)))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `time to full stamina rejects values above max`() {
        StaminaCalculator.timeToFullStamina(StaminaTime(42, 1))
    }
    // endregion

    // region StaminaTime
    @Test
    fun `stamina time totals and formats`() {
        val time = StaminaTime(15, 5)
        assertEquals(905, time.totalMinutes)
        assertEquals("15:05", time.format())
    }

    @Test
    fun `fromMinutes splits hours and minutes`() {
        assertEquals(StaminaTime(2, 5), StaminaTime.fromMinutes(125))
        assertEquals(StaminaTime(0, 0), StaminaTime.fromMinutes(0))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `stamina time rejects minutes out of range`() {
        StaminaTime(1, 60)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `stamina time rejects negative hours`() {
        StaminaTime(-1, 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `fromMinutes rejects negative values`() {
        StaminaTime.fromMinutes(-1)
    }
    // endregion
}
