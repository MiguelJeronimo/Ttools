package com.example.TibiaTools.utils

import android.view.View
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterInfoVisibilityTest {

    private val children = CharacterInfoVisibility.viewIds.associateWith { mockk<View>(relaxed = true) }
    private val root = mockk<View> {
        every { findViewById<View>(any()) } answers { children[firstArg<Int>()] }
    }

    @Test
    fun `view ids are unique`() {
        val ids = CharacterInfoVisibility.viewIds
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun `apply visible shows every view`() {
        CharacterInfoVisibility.apply(root, visible = true)
        children.values.forEach { verify(exactly = 1) { it.visibility = View.VISIBLE } }
    }

    @Test
    fun `apply hidden sets every view to gone`() {
        CharacterInfoVisibility.apply(root, visible = false)
        children.values.forEach { verify(exactly = 1) { it.visibility = View.GONE } }
    }

    @Test
    fun `missing views are ignored`() {
        val emptyRoot = mockk<View> { every { findViewById<View>(any()) } returns null }
        CharacterInfoVisibility.apply(emptyRoot, visible = true) // no lanza
        verify(exactly = CharacterInfoVisibility.viewIds.size) { emptyRoot.findViewById<View>(any()) }
    }
}
