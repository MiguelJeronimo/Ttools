package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewHighScores
import com.example.ttools.R
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class AdapterRecyclerViewHighScoresTest {

    private val ids = listOf(
        R.id.textViewRank, R.id.textViewName, R.id.textViewVocation,
        R.id.textViewLevel, R.id.textViewValue, R.id.textViewTittlee
    )
    private val adapter = AdapterRecyclerViewHighScores(
        listOf(ItemsRecyclerViewHighScores("1", "Bubble", "Elite Knight", "Antica", "1000", "Magic Level: 12", "Legend"))
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
        adapter.onBindViewHolder(AdapterRecyclerViewHighScores.ViewHolder(row.view), 0)

        verify { row.text(R.id.textViewRank).text = "1" }
        verify { row.text(R.id.textViewName).text = "Bubble" }
        verify { row.text(R.id.textViewVocation).text = "Elite Knight" }
        verify { row.text(R.id.textViewLevel).text = "Level: 1000" }
        verify { row.text(R.id.textViewValue).text = "Magic Level: 12" }
        verify { row.text(R.id.textViewTittlee).text = "Legend" }
    }

    @Test
    fun `onCreateViewHolder inflates the item layout`() {
        val row = FakeItemView(ids)
        val parent = stubInflation(R.layout.recyclerview_items_highscores, row.view)

        assertSame(row.view, adapter.onCreateViewHolder(parent, 0).itemView)
    }
}
