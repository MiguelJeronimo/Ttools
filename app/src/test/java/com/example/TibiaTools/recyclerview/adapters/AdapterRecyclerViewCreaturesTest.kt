package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewCreatures
import com.example.TibiaTools.utils.ImageLoader
import com.example.ttools.R
import io.mockk.mockk
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

class AdapterRecyclerViewCreaturesTest {

    private val textIds = listOf(R.id.textViewName, R.id.textViewRace)
    private val imageIds = listOf(R.id.imageViewCriature)
    private val imageLoader = mockk<ImageLoader>(relaxed = true)
    private val adapter = AdapterRecyclerViewCreatures(
        listOf(
            ItemsRecyclerViewCreatures("Dragon", "dragon", "https://example.com/dragon.gif"),
            ItemsRecyclerViewCreatures("Unknown", "unknown", "")
        ),
        imageLoader
    )

    @After
    fun tearDown() = unmockkStatic(LayoutInflater::class)

    @Test
    fun `item count matches list size`() {
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun `bind sets texts and loads image`() {
        val row = FakeItemView(textIds, imageIds)
        adapter.onBindViewHolder(AdapterRecyclerViewCreatures.ViewHolder(row.view, imageLoader), 0)

        verify { row.text(R.id.textViewName).text = "Dragon" }
        verify { row.text(R.id.textViewRace).text = "Race: dragon" }
        verify { imageLoader.load("https://example.com/dragon.gif", row.image(R.id.imageViewCriature)) }
    }

    @Test
    fun `empty image url is delegated to loader so it can clear the view`() {
        val row = FakeItemView(textIds, imageIds)
        adapter.onBindViewHolder(AdapterRecyclerViewCreatures.ViewHolder(row.view, imageLoader), 1)

        verify { imageLoader.load("", row.image(R.id.imageViewCriature)) }
    }

    @Test
    fun `onCreateViewHolder passes the injected loader and wires clicks`() {
        val row = FakeItemView(textIds, imageIds)
        val listener = mockk<View.OnClickListener>(relaxed = true)
        adapter.setOnClickListener(listener)

        val holder = adapter.onCreateViewHolder(stubInflation(R.layout.recyclerview_items_criatures, row.view), 0)
        adapter.onBindViewHolder(holder, 0)
        row.clickListener.captured.onClick(row.view)

        verify { imageLoader.load("https://example.com/dragon.gif", row.image(R.id.imageViewCriature)) }
        verify(exactly = 1) { listener.onClick(row.view) }
    }
}
