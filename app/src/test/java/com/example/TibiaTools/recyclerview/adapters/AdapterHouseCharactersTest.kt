package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import com.example.TibiaTools.recyclerview.items.ItemsHousesCharacters
import com.example.ttools.R
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class AdapterHouseCharactersTest {

    private val ids = listOf(R.id.houseName, R.id.houseCity, R.id.houseDateRent, R.id.idHouseRent)
    private val adapter = AdapterHouseCharacters(
        listOf(ItemsHousesCharacters("Sweet Home", "Thais", "2026-10-30", 10101))
    )

    @After
    fun tearDown() = unmockkStatic(LayoutInflater::class)

    @Test
    fun `item count matches list size`() {
        assertEquals(1, adapter.itemCount)
    }

    @Test
    fun `bind sets every field`() {
        val row = FakeItemView(ids)
        adapter.onBindViewHolder(AdapterHouseCharacters.ViewHolder(row.view), 0)

        verify { row.text(R.id.houseName).text = "Sweet Home" }
        verify { row.text(R.id.houseCity).text = "Thais" }
        verify { row.text(R.id.houseDateRent).text = "2026-10-30" }
        verify { row.text(R.id.idHouseRent).text = "10101" }
    }

    @Test
    fun `onCreateViewHolder inflates the item layout`() {
        val row = FakeItemView(ids)
        val parent = stubInflation(R.layout.items_houses_recyclerview_characters, row.view)

        assertSame(row.view, adapter.onCreateViewHolder(parent, 0).itemView)
    }
}
