package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewSpells
import com.example.TibiaTools.utils.GoldFormatter
import com.example.ttools.R
import io.mockk.mockk
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

class AdapterRecyclerViewSpellsTest {

    private val ids = listOf(
        R.id.textViewName, R.id.textViewFormula, R.id.textViewNivvel, R.id.textViewMana, R.id.textViewPrecio,
        R.id.textViewType, R.id.textViewGroup, R.id.textViewSpell_Id, R.id.textViewPremium
    )
    private val adapter = AdapterRecyclerViewSpells(
        listOf(
            ItemsRecyclerViewSpells("Light Healing", "exura", "20", "170", "Instant", "Healing", "lighthealing", "Free", "8"),
            ItemsRecyclerViewSpells("Mystery", "exori ?", "0", "unknown", "Instant", "Attack", "mystery", "Premium Only", "1")
        )
    )

    @After
    fun tearDown() = unmockkStatic(LayoutInflater::class)

    @Test
    fun `item count matches list size`() {
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun `bind sets every field`() {
        val row = FakeItemView(ids)
        adapter.onBindViewHolder(AdapterRecyclerViewSpells.ViewHolder(row.view), 0)

        verify { row.text(R.id.textViewName).text = "Light Healing" }
        verify { row.text(R.id.textViewFormula).text = "exura" }
        verify { row.text(R.id.textViewNivvel).text = "Level: 8" }
        verify { row.text(R.id.textViewMana).text = "Mana: 20" }
        verify { row.text(R.id.textViewPrecio).text = GoldFormatter.formatCoins("170") }
        verify { row.text(R.id.textViewType).text = "Instant" }
        verify { row.text(R.id.textViewGroup).text = "Healing" }
        verify { row.text(R.id.textViewSpell_Id).text = "lighthealing" }
        verify { row.text(R.id.textViewPremium).text = "Free" }
    }

    @Test
    fun `spell id view is hidden once when the holder is created`() {
        val row = FakeItemView(ids)
        val holder = AdapterRecyclerViewSpells.ViewHolder(row.view)
        adapter.onBindViewHolder(holder, 0)
        adapter.onBindViewHolder(holder, 1)

        verify(exactly = 1) { row.text(R.id.textViewSpell_Id).visibility = View.INVISIBLE }
    }

    @Test
    fun `non numeric price does not crash`() {
        val row = FakeItemView(ids)
        adapter.onBindViewHolder(AdapterRecyclerViewSpells.ViewHolder(row.view), 1)

        verify { row.text(R.id.textViewPrecio).text = "unknown" }
    }

    @Test
    fun `row click is forwarded to listener`() {
        val row = FakeItemView(ids)
        val listener = mockk<View.OnClickListener>(relaxed = true)
        adapter.setOnClickListener(listener)

        adapter.onCreateViewHolder(stubInflation(R.layout.recyclerview_spells_tibia, row.view), 0)
        row.clickListener.captured.onClick(row.view)

        verify(exactly = 1) { listener.onClick(row.view) }
    }
}
