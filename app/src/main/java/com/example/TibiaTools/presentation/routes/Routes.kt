package com.example.TibiaTools.presentation.routes

import android.os.Parcelable
import androidx.navigation3.runtime.NavKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed class HomeRoutes: NavKey, Parcelable {
    @Serializable
    @Parcelize
    object Home: HomeRoutes()
    @Serializable
    @Parcelize
    object Tools: HomeRoutes()
    @Serializable
    @Parcelize
    object Players: HomeRoutes()
    @Serializable
    @Parcelize
    object Ranks: HomeRoutes()
    @Serializable
    @Parcelize
    object Library: HomeRoutes()
    @Serializable
    @Parcelize
    data class SpellProfile(val spellId: String): HomeRoutes()
    @Serializable
    @Parcelize
    data class CreatureProfile(val name: String): HomeRoutes()
}