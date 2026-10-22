package com.example.TibiaTools.operations

import org.junit.Assert.assertEquals
import org.junit.Test

class BlessingCalculatorTest {

    // region regularBlessingCost
    @Test
    fun `regular blessing has flat cost up to level 30`() {
        assertEquals(2_000L, BlessingCalculator.regularBlessingCost(1))
        assertEquals(2_000L, BlessingCalculator.regularBlessingCost(30))
    }

    @Test
    fun `regular blessing grows 200 per level between 31 and 119`() {
        assertEquals(2_200L, BlessingCalculator.regularBlessingCost(31))
        assertEquals(16_000L, BlessingCalculator.regularBlessingCost(100))
        assertEquals(19_800L, BlessingCalculator.regularBlessingCost(119))
    }

    @Test
    fun `regular blessing grows 75 per level from 120`() {
        assertEquals(20_000L, BlessingCalculator.regularBlessingCost(120))
        assertEquals(22_250L, BlessingCalculator.regularBlessingCost(150))
        assertEquals(126_500L, BlessingCalculator.regularBlessingCost(1_540))
    }
    // endregion

    // region twistOfFateCost
    @Test
    fun `twist of fate follows regular formula below 120`() {
        assertEquals(2_000L, BlessingCalculator.twistOfFateCost(30))
        assertEquals(16_000L, BlessingCalculator.twistOfFateCost(100))
    }

    @Test
    fun `twist of fate is capped at 20000 from level 120`() {
        assertEquals(20_000L, BlessingCalculator.twistOfFateCost(120))
        assertEquals(20_000L, BlessingCalculator.twistOfFateCost(2_000))
    }
    // endregion

    // region enhancedBlessingCost
    @Test
    fun `enhanced blessing tiers`() {
        assertEquals(2_600L, BlessingCalculator.enhancedBlessingCost(30))
        assertEquals(2_860L, BlessingCalculator.enhancedBlessingCost(31))
        assertEquals(25_740L, BlessingCalculator.enhancedBlessingCost(119))
        assertEquals(26_000L, BlessingCalculator.enhancedBlessingCost(120))
        assertEquals(29_000L, BlessingCalculator.enhancedBlessingCost(150))
    }
    // endregion

    // region calculate
    @Test
    fun `calculate without enhanced blessings`() {
        val costs = BlessingCalculator.calculate(level = 100)

        assertEquals(16_000L, costs.regularBlessing)
        assertEquals(80_000L, costs.regularBlessingsTotal)
        assertEquals(16_000L, costs.twistOfFate)
        assertEquals(0L, costs.heartOfTheMountain)
        assertEquals(0L, costs.bloodOfTheMountain)
        assertEquals(96_000L, costs.total)
    }

    @Test
    fun `calculate with only heart of the mountain`() {
        val costs = BlessingCalculator.calculate(150, includeHeartOfTheMountain = true)

        assertEquals(29_000L, costs.heartOfTheMountain)
        assertEquals(0L, costs.bloodOfTheMountain)
        assertEquals(111_250L + 20_000L + 29_000L, costs.total)
    }

    @Test
    fun `calculate with both enhanced blessings`() {
        val costs = BlessingCalculator.calculate(
            level = 150,
            includeHeartOfTheMountain = true,
            includeBloodOfTheMountain = true
        )

        assertEquals(29_000L, costs.heartOfTheMountain)
        assertEquals(29_000L, costs.bloodOfTheMountain)
        assertEquals(189_250L, costs.total)
    }

    @Test
    fun `calculate does not overflow for very high levels`() {
        val costs = BlessingCalculator.calculate(Int.MAX_VALUE, true, true)

        val expectedRegular = 20_000L + 75L * (Int.MAX_VALUE - 120L)
        assertEquals(expectedRegular, costs.regularBlessing)
        assertEquals(expectedRegular * 5, costs.regularBlessingsTotal)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `calculate rejects level zero`() {
        BlessingCalculator.calculate(0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `cost functions reject negative levels`() {
        BlessingCalculator.regularBlessingCost(-5)
    }
    // endregion
}
