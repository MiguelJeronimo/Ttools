package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewHouses
import com.example.ttools.R
import io.mockk.mockk
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

class AdapterRecyclerViewHousesTest {

    private val ids = listOf(R.id.house_name, R.id.house_size, R.id.house_rent, R.id.house_rented, R.id.house_id)
    private val adapter = AdapterRecyclerViewHouses(
        listOf(
            ItemsRecyclerViewHouses("Sweet Home", "40", "50000", ItemsRecyclerViewHouses.rentedLabel(true), "10101"),
            ItemsRecyclerViewHouses("Free Flat", "20", "25000", ItemsRecyclerViewHouses.rentedLabel(false), "10102")
        )
    )

    @After
    fun tearDown() = unmockkStatic(LayoutInflater::class)

    @Test
    fun `item count matches list size`() {
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun `bind rented house`() {
        val row = FakeItemView(ids)
        adapter.onBindViewHolder(AdapterRecyclerViewHouses.ViewHolder(row.view), 0)

        verify { row.text(R.id.house_name).text = "Sweet Home" }
        verify { row.text(R.id.house_size).text = "40" }
        verify { row.text(R.id.house_rent).text = "50000" }
        verify { row.text(R.id.house_rented).text = ItemsRecyclerViewHouses.RENTED }
        verify { row.text(R.id.house_rented).setTextColor(AdapterRecyclerViewHouses.RENTED_COLOR) }
        verify { row.text(R.id.house_id).text = "10101" }
    }

    @Test
    fun `bind available house uses available color`() {
        val row = FakeItemView(ids)
        adapter.onBindViewHolder(AdapterRecyclerViewHouses.ViewHolder(row.view), 1)

        verify { row.text(R.id.house_rented).text = ItemsRecyclerViewHouses.AVAILABLE }
        verify { row.text(R.id.house_rented).setTextColor(AdapterRecyclerViewHouses.AVAILABLE_COLOR) }
    }

    @Test
    fun `row click is forwarded to listener`() {
        val row = FakeItemView(ids)
        val listener = mockk<View.OnClickListener>(relaxed = true)
        adapter.setOnClickListener(listener)

        adapter.onCreateViewHolder(stubInflation(R.layout.recyclerview_items_houses, row.view), 0)
        row.clickListener.captured.onClick(row.view)

        verify(exactly = 1) { listener.onClick(row.view) }
    }

    @Test
    fun `clearing the listener stops forwarding clicks`() {
        val row = FakeItemView(ids)
        val listener = mockk<View.OnClickListener>(relaxed = true)
        adapter.setOnClickListener(listener)
        adapter.onCreateViewHolder(stubInflation(R.layout.recyclerview_items_houses, row.view), 0)

        adapter.setOnClickListener(null)
        row.clickListener.captured.onClick(row.view)

        verify(exactly = 0) { listener.onClick(any()) }
    }
}
