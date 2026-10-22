package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import com.example.TibiaTools.recyclerview.items.ItemsAchievementsCharacter
import com.example.ttools.R
import io.mockk.unmockkStatic
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class AdapterAchievementsCharacterTest {

    private val ids = listOf(R.id.achievementsName, R.id.achievementsGrade, R.id.achievementsSecret)
    private val items = listOf(
        ItemsAchievementsCharacter("Allow Cookies?", "1", secret = true),
        ItemsAchievementsCharacter("Bearhugger", "2", secret = false)
    )
    private val adapter = AdapterAchievementsCharacter(items)

    @After
    fun tearDown() = unmockkStatic(LayoutInflater::class)

    @Test
    fun `item count matches list size`() {
        assertEquals(2, adapter.itemCount)
        assertEquals(0, AdapterAchievementsCharacter(emptyList()).itemCount)
    }

    @Test
    fun `bind sets name grade and secret label`() {
        val row = FakeItemView(ids)
        adapter.onBindViewHolder(AdapterAchievementsCharacter.ViewHolder(row.view), 0)

        verify { row.text(R.id.achievementsName).text = "Allow Cookies?" }
        verify { row.text(R.id.achievementsGrade).text = "1" }
        verify { row.text(R.id.achievementsSecret).text = AdapterAchievementsCharacter.SECRET_LABEL }
    }

    @Test
    fun `recycled holder clears secret label`() {
        val row = FakeItemView(ids)
        val holder = AdapterAchievementsCharacter.ViewHolder(row.view)

        adapter.onBindViewHolder(holder, 0)
        adapter.onBindViewHolder(holder, 1)

        verifyOrder {
            row.text(R.id.achievementsSecret).text = AdapterAchievementsCharacter.SECRET_LABEL
            row.text(R.id.achievementsSecret).text = ""
        }
    }

    @Test
    fun `onCreateViewHolder inflates the item layout`() {
        val row = FakeItemView(ids)
        val parent = stubInflation(R.layout.items_achievements_character, row.view)

        assertSame(row.view, adapter.onCreateViewHolder(parent, 0).itemView)
    }
}
