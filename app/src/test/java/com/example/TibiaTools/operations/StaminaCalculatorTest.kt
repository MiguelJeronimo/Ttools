package com.example.TibiaTools.operations

import com.example.TibiaTools.utils.ex.StaminaLevel
import com.example.TibiaTools.utils.ex.level
import com.example.TibiaTools.utils.ex.progress
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

    // region timeToGreenStamina
    @Test
    fun `time to green stamina from below green`() {
        // 37:20 (2240 min) -> falta 100 min -> 100 * 3 + 10 = 310 min = 5:10
        assertEquals(StaminaTime(5, 10), StaminaCalculator.timeToGreenStamina(StaminaTime(37, 20)))
    }

    @Test
    fun `time to green stamina from zero`() {
        // 0:00 (0 min) -> falta 2340 min -> 2340 * 3 + 10 = 7030 min = 117:10
        assertEquals(StaminaTime(117, 10), StaminaCalculator.timeToGreenStamina(StaminaTime(0, 0)))
    }

    @Test
    fun `time to green stamina when already in green`() {
        assertEquals(StaminaTime(0, 0), StaminaCalculator.timeToGreenStamina(StaminaTime(39, 0)))
        assertEquals(StaminaTime(0, 0), StaminaCalculator.timeToGreenStamina(StaminaTime(40, 0)))
        assertEquals(StaminaTime(0, 0), StaminaCalculator.timeToGreenStamina(StaminaTime(42, 0)))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `time to green stamina rejects values above max`() {
        StaminaCalculator.timeToGreenStamina(StaminaTime(42, 1))
    }
    // endregion

    // region timeToFullStamina
    @Test
    fun `time to full stamina from green range 39 30`() {
        // Faltan 150 min -> 150 * 6 + 10 = 910 min = 15:10
        assertEquals(StaminaTime(15, 10), StaminaCalculator.timeToFullStamina(StaminaTime(39, 30)))
    }

    @Test
    fun `time to full stamina from normal range 37 20`() {
        // Normal (2340-2240=100 min) * 3 = 300 min
        // Green 180 min * 6 = 1080 min
        // Delay = 10 min
        // Total = 1390 min = 23:10
        assertEquals(StaminaTime(23, 10), StaminaCalculator.timeToFullStamina(StaminaTime(37, 20)))
    }

    @Test
    fun `time to full stamina from one minute below max`() {
        assertEquals(StaminaTime(0, 16), StaminaCalculator.timeToFullStamina(StaminaTime(41, 59)))
    }

    @Test
    fun `time to full stamina from zero`() {
        // Normal: 2340 * 3 = 7020 min
        // Green: 180 * 6 = 1080 min
        // Delay: 10 min
        // Total = 8110 min = 135:10
        assertEquals(StaminaTime(135, 10), StaminaCalculator.timeToFullStamina(StaminaTime(0, 0)))
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
    fun `stamina time percent`() {
        assertEquals(0.0f, StaminaTime(0, 0).percent(), 0.001f)
        assertEquals(0.5f, StaminaTime(21, 0).percent(), 0.001f)
        assertEquals(1.0f, StaminaTime(42, 0).percent(), 0.001f)
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

    // region StaminaEx extensions
    @Test
    fun `stamina level classification`() {
        assertEquals(StaminaLevel.GREEN, StaminaTime(42, 0).level())
        assertEquals(StaminaLevel.GREEN, StaminaTime(39, 0).level())
        assertEquals(StaminaLevel.ORANGE, StaminaTime(38, 59).level())
        assertEquals(StaminaLevel.ORANGE, StaminaTime(14, 0).level())
        assertEquals(StaminaLevel.RED, StaminaTime(13, 59).level())
        assertEquals(StaminaLevel.RED, StaminaTime(0, 1).level())
        assertEquals(StaminaLevel.EMPTY, StaminaTime(0, 0).level())
    }

    @Test
    fun `stamina progress extension`() {
        assertEquals(0.0f, StaminaTime(0, 0).progress(), 0.001f)
        assertEquals(0.5f, StaminaTime(21, 0).progress(), 0.001f)
        assertEquals(1.0f, StaminaTime(42, 0).progress(), 0.001f)
    }
    // endregion
}
