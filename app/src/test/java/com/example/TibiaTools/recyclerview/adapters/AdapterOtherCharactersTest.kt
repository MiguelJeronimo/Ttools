package com.example.TibiaTools.recyclerview.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import com.example.TibiaTools.recyclerview.items.ItemsCharacters
import com.example.ttools.R
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkStatic
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class AdapterOtherCharactersTest {

    private val ids = listOf(
        R.id.nameCharater, R.id.characterStatus, R.id.characterDelete, R.id.characterMain, R.id.characterWorld
    )
    private val main = ItemsCharacters("Bubble", "Antica", "online", deleted = true, main = true, traded = false)
    private val alt = ItemsCharacters("Cachero", "Secura", "offline", deleted = false, main = false, traded = true)
    private val adapter = AdapterOtherCharacters(listOf(main, alt))

    @After
    fun tearDown() = unmockkStatic(LayoutInflater::class)

    @Test
    fun `item count matches list size`() {
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun `bind main online character`() {
        val row = FakeItemView(ids)
        adapter.onBindViewHolder(AdapterOtherCharacters.ViewHolder(row.view), 0)

        verify { row.text(R.id.nameCharater).text = "Bubble" }
        verify { row.text(R.id.characterWorld).text = "Antica" }
        verify { row.text(R.id.characterStatus).text = "online" }
        verify { row.text(R.id.characterStatus).setTextColor(Color.GREEN) }
        verify { row.text(R.id.characterMain).text = AdapterOtherCharacters.MAIN_LABEL }
        verify { row.text(R.id.characterMain).setTextColor(AdapterOtherCharacters.MAIN_COLOR) }
        verify { row.text(R.id.characterDelete).text = AdapterOtherCharacters.DELETED_LABEL }
    }

    @Test
    fun `recycled holder resets labels and status color`() {
        val row = FakeItemView(ids)
        val defaultColors = mockk<ColorStateList>()
        every { row.text(R.id.characterStatus).textColors } returns defaultColors
        val holder = AdapterOtherCharacters.ViewHolder(row.view)

        adapter.onBindViewHolder(holder, 0)
        adapter.onBindViewHolder(holder, 1)

        verifyOrder {
            row.text(R.id.characterStatus).setTextColor(Color.GREEN)
            row.text(R.id.characterStatus).setTextColor(defaultColors)
        }
        verify { row.text(R.id.characterMain).text = "" }
        verify { row.text(R.id.characterDelete).text = "" }
    }

    @Test
    fun `onCreateViewHolder inflates the item layout`() {
        val row = FakeItemView(ids)
        val parent = stubInflation(R.layout.items_characters_list, row.view)

        assertSame(row.view, adapter.onCreateViewHolder(parent, 0).itemView)
    }
}
