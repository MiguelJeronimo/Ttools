package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewGuilds
import com.example.TibiaTools.utils.ImageLoader
import com.example.ttools.R
import io.mockk.mockk
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class AdapterRecyclerViewGuildsListTest {

    private val textIds = listOf(R.id.textViewName, R.id.textViewDescription)
    private val imageIds = listOf(R.id.imageViewLogo)
    private val loaded = mutableListOf<Pair<String, Any>>()
    private val imageLoader = ImageLoader { url, target -> loaded += url to target }
    private val adapter = AdapterRecyclerViewGuildsList(
        listOf(ItemsRecyclerViewGuilds("Red Rose", "https://example.com/logo.gif", "PvP guild")),
        imageLoader
    )

    @After
    fun tearDown() = unmockkStatic(LayoutInflater::class)

    @Test
    fun `item count matches list size`() {
        assertEquals(1, adapter.itemCount)
    }

    @Test
    fun `bind sets texts and loads logo`() {
        val row = FakeItemView(textIds, imageIds)
        adapter.onBindViewHolder(AdapterRecyclerViewGuildsList.ViewHolder(row.view, imageLoader), 0)

        verify { row.text(R.id.textViewName).text = "Red Rose" }
        verify { row.text(R.id.textViewDescription).text = "PvP guild" }
        assertEquals(listOf("https://example.com/logo.gif" to row.image(R.id.imageViewLogo)), loaded)
    }

    @Test
    fun `row click is forwarded to listener set after creation`() {
        val row = FakeItemView(textIds, imageIds)
        val parent = stubInflation(R.layout.recyclerview_items_guilds, row.view)
        val holder = adapter.onCreateViewHolder(parent, 0)
        val listener = mockk<View.OnClickListener>(relaxed = true)

        adapter.setOnClickListener(listener)
        row.clickListener.captured.onClick(row.view)

        assertSame(row.view, holder.itemView)
        verify(exactly = 1) { listener.onClick(row.view) }
    }

    @Test
    fun `row click without listener does nothing`() {
        val row = FakeItemView(textIds, imageIds)
        adapter.onCreateViewHolder(stubInflation(R.layout.recyclerview_items_guilds, row.view), 0)

        row.clickListener.captured.onClick(row.view) // no lanza
    }
}
