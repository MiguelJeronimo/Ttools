package com.example.TibiaTools.recyclerview.adapters

import android.graphics.Color
import android.view.LayoutInflater
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewGuildMembers
import com.example.ttools.R
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class AdapterRecyclerViewGuildMembersTest {

    private val ids = listOf(
        R.id.textViewGuildName, R.id.textViewTitulo, R.id.textViewRango, R.id.textViewVocation,
        R.id.textViewLevel, R.id.textViewStatus, R.id.textViewUnido
    )
    private val adapter = AdapterRecyclerViewGuildMembers(
        listOf(
            ItemsRecyclerViewGuildMembers("Bubble", "Boss", "Leader", "Elite Knight", 500, "2020-01-01", "online"),
            ItemsRecyclerViewGuildMembers("Cachero", "", "Member", "Druid", 300, "2021-05-05", "offline")
        )
    )

    @After
    fun tearDown() = unmockkStatic(LayoutInflater::class)

    @Test
    fun `item count matches list size`() {
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun `bind online member`() {
        val row = FakeItemView(ids)
        adapter.onBindViewHolder(AdapterRecyclerViewGuildMembers.ViewHolder(row.view), 0)

        verify { row.text(R.id.textViewGuildName).text = "Bubble" }
        verify { row.text(R.id.textViewTitulo).text = "Titulo: Boss" }
        verify { row.text(R.id.textViewRango).text = "Rango: Leader" }
        verify { row.text(R.id.textViewVocation).text = "Elite Knight" }
        verify { row.text(R.id.textViewLevel).text = "Level: 500" }
        verify { row.text(R.id.textViewStatus).text = "Status: online" }
        verify { row.text(R.id.textViewStatus).setTextColor(Color.GREEN) }
        verify { row.text(R.id.textViewUnido).text = "Unido: 2020-01-01" }
    }

    @Test
    fun `bind offline member uses offline color`() {
        val row = FakeItemView(ids)
        adapter.onBindViewHolder(AdapterRecyclerViewGuildMembers.ViewHolder(row.view), 1)

        verify { row.text(R.id.textViewStatus).setTextColor(AdapterRecyclerViewGuildMembers.OFFLINE_COLOR) }
    }

    @Test
    fun `onCreateViewHolder inflates the item layout`() {
        val row = FakeItemView(ids)
        val parent = stubInflation(R.layout.recyclerview_guilds_members, row.view)

        assertSame(row.view, adapter.onCreateViewHolder(parent, 0).itemView)
    }
}
