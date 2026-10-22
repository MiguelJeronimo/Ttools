package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewNews
import com.example.ttools.R
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class AdapterRecyclerViewNewsTest {

    private val ids = listOf(R.id.textViewFecha, R.id.textViewCategoria, R.id.textViewNoticia, R.id.textViewTipo)
    private val adapter = AdapterRecyclerViewNews(
        listOf(ItemsRecyclerViewNews("8000", "2026-10-14", "Server save delayed", "technical", "ticker", "https://x"))
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
        adapter.onBindViewHolder(AdapterRecyclerViewNews.ViewHolder(row.view), 0)

        verify { row.text(R.id.textViewFecha).text = "2026-10-14" }
        verify { row.text(R.id.textViewNoticia).text = "Server save delayed" }
        verify { row.text(R.id.textViewCategoria).text = "technical" }
        verify { row.text(R.id.textViewTipo).text = "ticker" }
    }

    @Test
    fun `adapter reflects mutations of the backing list`() {
        val backing = mutableListOf<ItemsRecyclerViewNews>()
        val mutableAdapter = AdapterRecyclerViewNews(backing)
        assertEquals(0, mutableAdapter.itemCount)

        backing += ItemsRecyclerViewNews("1", "d", "n", "c", "t", "u")
        assertEquals(1, mutableAdapter.itemCount)
    }

    @Test
    fun `onCreateViewHolder inflates the item layout`() {
        val row = FakeItemView(ids)
        val parent = stubInflation(R.layout.recyclerview_news_items, row.view)

        assertSame(row.view, adapter.onCreateViewHolder(parent, 0).itemView)
    }
}
