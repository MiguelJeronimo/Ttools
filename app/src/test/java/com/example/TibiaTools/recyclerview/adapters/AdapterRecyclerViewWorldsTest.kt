package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewWorlds
import com.example.ttools.R
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class AdapterRecyclerViewWorldsTest {

    private val ids = listOf(
        R.id.world, R.id.status, R.id.location, R.id.pvptype, R.id.premium_only, R.id.transfer_type,
        R.id.player_online, R.id.game_world_type, R.id.battleeye_protected, R.id.battleeye_date,
        R.id.tournament_world_type
    )
    private val adapter = AdapterRecyclerViewWorlds(
        listOf(
            ItemsRecyclerViewWorlds(
                name = "Antica", status = "online", playersOnline = "512", location = "Europe",
                pvpType = "Open PvP", premiumOnly = "false", transferType = "regular",
                battleyeProtected = "true", battleyeDate = "release", gameWorldType = "regular",
                tournamentWorldType = "none"
            )
        )
    )

    @After
    fun tearDown() = unmockkStatic(LayoutInflater::class)

    @Test
    fun `item count matches list size`() {
        assertEquals(1, adapter.itemCount)
    }

    @Test
    fun `bind sets every labelled field`() {
        val row = FakeItemView(ids)
        adapter.onBindViewHolder(AdapterRecyclerViewWorlds.ViewHolder(row.view), 0)

        verify { row.text(R.id.world).text = "Antica" }
        verify { row.text(R.id.status).text = "online" }
        verify { row.text(R.id.location).text = "Location: Europe" }
        verify { row.text(R.id.pvptype).text = "Pvp type: Open PvP" }
        verify { row.text(R.id.premium_only).text = "Premium only: false" }
        verify { row.text(R.id.transfer_type).text = "Transfer type: regular" }
        verify { row.text(R.id.player_online).text = "Players Online: 512" }
        verify { row.text(R.id.game_world_type).text = "Game world type: regular" }
        verify { row.text(R.id.battleeye_protected).text = "BattleEye protected: true" }
        verify { row.text(R.id.battleeye_date).text = "BattleEye date: release" }
        verify { row.text(R.id.tournament_world_type).text = "Tournament world type: none" }
    }

    @Test
    fun `onCreateViewHolder inflates the item layout`() {
        val row = FakeItemView(ids)
        val parent = stubInflation(R.layout.recyclerview_items_mundo, row.view)

        assertSame(row.view, adapter.onCreateViewHolder(parent, 0).itemView)
    }
}
