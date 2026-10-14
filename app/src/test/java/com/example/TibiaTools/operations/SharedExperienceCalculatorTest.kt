package com.example.TibiaTools.operations

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.ceil
import kotlin.math.floor

class SharedExperienceCalculatorTest {

    @Test
    fun `range for level 100`() {
        assertEquals(SharedExperienceRange(67, 150), SharedExperienceCalculator.calculate(100))
    }

    @Test
    fun `range rounds min up and max down`() {
        // 2/3 × 101 = 67.33 → 68 ; 3/2 × 101 = 151.5 → 151
        assertEquals(SharedExperienceRange(68, 151), SharedExperienceCalculator.calculate(101))
    }

    @Test
    fun `range for level 1`() {
        assertEquals(SharedExperienceRange(1, 1), SharedExperienceCalculator.calculate(1))
    }

    @Test
    fun `integer arithmetic matches the original ceil and floor formula`() {
        for (level in 1..5_000) {
            val range = SharedExperienceCalculator.calculate(level)
            assertEquals("min @ $level", ceil(level * 2.0 / 3).toInt(), range.minLevel)
            assertEquals("max @ $level", floor(level * 3.0 / 2).toInt(), range.maxLevel)
        }
    }

    @Test
    fun `max level is clamped instead of overflowing`() {
        val range = SharedExperienceCalculator.calculate(Int.MAX_VALUE)
        assertEquals(Int.MAX_VALUE, range.maxLevel)
        assertTrue(range.minLevel > 0)
    }

    @Test
    fun `contains is inclusive on both ends`() {
        val range = SharedExperienceCalculator.calculate(100)
        assertTrue(67 in range)
        assertTrue(150 in range)
        assertFalse(66 in range)
        assertFalse(151 in range)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `rejects level zero`() {
        SharedExperienceCalculator.calculate(0)
    }
}
